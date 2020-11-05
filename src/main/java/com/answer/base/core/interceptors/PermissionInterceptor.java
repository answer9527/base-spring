package com.answer.base.core.interceptors;

import com.answer.base.exception.http.ParameterException;
import com.answer.base.exception.http.TokenException;
import com.answer.base.util.JwtToken;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

// 拦截器这边可以做  scope权限  上次在线时间 日志 等问题
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevelOptional = this.getScopeLevel(handler);
        if(!scopeLevelOptional.isPresent()){
            return true;
        }
        String bearer_token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(bearer_token)){
            throw new TokenException(40001);
        }
        if(!bearer_token.startsWith("Bearer")){
            throw new TokenException(40002);
        }
        String token = bearer_token.split(" ")[1];
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaim(token);
        Map<String,Claim> map = optionalMap.orElseThrow(()->new TokenException(40003));
        return this.hasPermission(scopeLevelOptional.get(),map);
//        return super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

//    获取方法上的scopelevel注解的值
    public Optional<ScopeLevel> getScopeLevel(Object handler){
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if(scopeLevel==null){
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

//    比较注解上的scopeLevel和token中的解析出的scopeLevel
    private boolean hasPermission(ScopeLevel scopeLevel,Map<String,Claim> map){
        Integer scope = map.get("scope").asInt();
        Integer level = scopeLevel.value();
        if(scope<level){
            throw new ParameterException(40010);
        }
        return true;
    }
}
