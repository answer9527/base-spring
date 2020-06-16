package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/v1/classic")
public class ClassicController {
    @Autowired
    private ClassicService classicService;

//    获取最新的推荐
    @GetMapping("/latest")
    @ScopeLevel(value = 10)
    public Msg getRecommendLatest(){
        Classic classic = classicService.getRecommendLatest();
        return ResultUtil.success(classic);
    }

//    获取推荐的上一个
    @GetMapping("/{id}/previous")
    public Msg getRecommendPrevious(@PathVariable Integer id){
        Classic classic = classicService.getRecommendPrevious(id);
        return ResultUtil.success(classic);
    }

//    获取推荐的下一个
    @GetMapping("/{id}/next")
    public Msg getRecommendNext(@PathVariable Integer id){
        Classic classic = classicService.getRecommendNext(id);
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
    @GetMapping("/setLike/{id}")
    public void setLike(@PathVariable Integer id){

    }
}
