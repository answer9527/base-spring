package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import com.answer.base.service.CommentService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/classic")
public class ClassicController {
    @Autowired
    private ClassicService classicService;


//    获取最新的推荐
    @GetMapping("/latest")
//    @ScopeLevel(value = 10)
    public Msg getRecommendLatest(HttpServletRequest request){
        Classic classic = classicService.getRecommendLatest();
        String token = request.getHeader("Authorization");
        if(!token.equals("")){
            classic=this.formatLikeStatus(classic,token);
        }
        return ResultUtil.success(classic);
    }

//    获取推荐的上一个
    @GetMapping("/{id}/previous")
    public Msg getRecommendPrevious(HttpServletRequest request,@PathVariable Integer id){
        Classic classic = classicService.getRecommendPrevious(id);
        String token = request.getHeader("Authorization");
        if(!token.equals("")){
            classic=this.formatLikeStatus(classic,token);
        }
        return ResultUtil.success(classic);
    }

//    获取推荐的下一个
    @GetMapping("/{id}/next")
    public Msg getRecommendNext(HttpServletRequest request,@PathVariable Integer id){
        Classic classic = classicService.getRecommendNext(id);
        String token = request.getHeader("Authorization");
        if(!token.equals("")){
            classic=this.formatLikeStatus(classic,token);
        }
        return ResultUtil.success(classic);
    }

//    新增一条数据
    @PostMapping("/add")
    public Msg add(@RequestBody Classic classic){
        classicService.insertClassic(classic);
        return ResultUtil.success("新增成功");
    }

//    设置推荐
    @GetMapping("/setRecommend")
    public Msg setRecommendOne(@RequestParam Integer id){
        classicService.RecommendClassic(id);
        return ResultUtil.success("设置推荐成功");
    }

//    下架推荐
    @GetMapping("/removeRecommend")
    public Msg removeRecommend(@RequestParam Integer id){
        classicService.removeRecommend(id);
        return ResultUtil.success("取消推荐成功");
    }

//    删除一条
    @GetMapping("/delete")
    public Msg deleteOne(@RequestParam Integer id){
        classicService.deleteClassic(id);
        return ResultUtil.success("删除成功");
    }




//    设置喜欢
    @GetMapping("/setLike/{cid}")
    public Msg setLike(HttpServletRequest request,@PathVariable Integer cid){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        classicService.setLike(uid,cid);
        return ResultUtil.success("设置成功");
    }

//    设置取消喜欢
    @GetMapping("/cancelLike/{cid}")
    public Msg cancelLike(HttpServletRequest request,@PathVariable Integer cid){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        classicService.cancelLike(uid,cid);
        return ResultUtil.success("取消成功");
    }

//    查找我喜欢的
    @PostMapping("/getMyLike")
    @ScopeLevel(4)
    public Msg getMyLiKe(HttpServletRequest request, @RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager<Classic> classicList = classicService.getMyLike(pagingDTO);
        return ResultUtil.success(classicList);
    }

//    按类型查找列表
    @PostMapping("/getByType")
    public Msg getByType(@RequestBody PagingDTO pagingDTO){
        Pager<Classic> classicList = classicService.getListByType(pagingDTO);
        return ResultUtil.success(classicList);
    }

//    根据id查询详情
    @GetMapping("/detail/{id}")
    public Msg getDetailById(HttpServletRequest request,@PathVariable Integer id){
        Classic classic = classicService.getDetailById(id);
        String token = request.getHeader("Authorization");
        if(!token.equals("")){
            classic=this.formatLikeStatus(classic,token);
        }
        return ResultUtil.success(classic);
    }

//    设置返回是否喜欢某classic
    public Classic formatLikeStatus(Classic classic,String token){
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setId(classic.getId());
        Integer uid = JwtToken.TokenGetUid(token);
        uidAndIdDTO.setUid(uid);
        Boolean like_status = classicService.getLikeStatus(uidAndIdDTO);
        classic.setLike_status(like_status);
        return classic;
    }

}
