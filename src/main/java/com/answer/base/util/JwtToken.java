package com.answer.base.util;

import com.answer.base.exception.http.TokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.*;

@Configuration
public class JwtToken {
    protected static String jwtKey;
    protected static Integer defaultScope;
    protected static Integer expiredTimeIn;

    @Value("${token.security.jwt-key}")
    public  void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }
    @Value("${token.security.default-scope}")
    public  void setDefaultScope(Integer defaultScope) {
        JwtToken.defaultScope = defaultScope;
    }
    @Value("${token.security.token-expired-in}")
    public  void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    public static Optional<Map<String,Claim>> getClaim(String token){
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            decodedJWT = jwtVerifier.verify(token);
        }catch (JWTDecodeException e){
            return Optional.empty();
        }
        return Optional.of(decodedJWT.getClaims());
    }


    public static String makeToken(Integer uid){
        Map<String,Date> map = JwtToken.calculateExpiredIssued();
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        String token = JWT.create().
                withIssuedAt(map.get("now")).
                withExpiresAt(map.get("future")).
                withClaim("uid",uid).
                withClaim("scope",JwtToken.defaultScope).
                sign(algorithm);
        return token;
    }
    public static String makeToken(Integer uid,Integer scope){
        Map<String,Date> map = JwtToken.calculateExpiredIssued();
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        String token = JWT.create().
                withIssuedAt(map.get("now")).
                withExpiresAt(map.get("future")).
                withClaim("uid",uid).
                withClaim("scope",scope).
                sign(algorithm);
        return token;
    }

//    设置token时间生效期
    public static Map<String, Date> calculateExpiredIssued(){
        Map<String,Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(calendar.SECOND,JwtToken.expiredTimeIn);
        Date future = calendar.getTime();
        map.put("now",now);
        map.put("future",future);
        return map;
    }

//    解析token中的获取uid
    public static Integer TokenGetUid(String bearer_token){
        if(StringUtils.isEmpty(bearer_token)){
            throw new TokenException(40001);
        }
        if(!bearer_token.startsWith("Bearer")){
            throw new TokenException(40002);
        }
        String token = bearer_token.split(" ")[1];
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaim(token);
        Map<String,Claim> map = optionalMap.orElseThrow(()->new TokenException(40003));
        Integer uid = map.get("uid").asInt();
        return uid;
    }
}
