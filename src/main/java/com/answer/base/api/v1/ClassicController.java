package com.answer.base.api.v1;

import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/classic")
public class ClassicController {
    @Autowired
    private ClassicService classicService;

//    获取最新的推荐
    @GetMapping("/latest")
    public Classic getRecommendLatest(){
        Classic classic = classicService.getRecommendLatest();
        return classic;
    }

//    获取推荐的上一个
    @GetMapping("/{id}/previous")
    public Classic getRecommendPrevious(@PathVariable Integer id){
        return classicService.getRecommendPrevious(id);
    }

//    获取推荐的上一个
    @GetMapping("/{id}/next")
    public Classic getRecommendNext(@PathVariable Integer id){
        return classicService.getRecommendNext(id);
    }

//    新增一条数据
    @PostMapping("/add")
    public void add(@RequestBody Classic classic){
        Integer status = classicService.insertClassic(classic);
        if(status!=0){

        }else{

        }
    }

//    设置推荐
    @GetMapping("/setRecommend")
    public void setRecommendOne(@RequestParam Integer id){
        Integer status = classicService.RecommendClassic(id);
    }

//    下架推荐
    @GetMapping("/removeRecommend")
    public void removeRecommend(@RequestParam Integer id){
        Integer status = classicService.removeRecommend(id);
    }

//    删除一条
    @GetMapping("/delete")
    public void deleteOne(@RequestParam Integer id){
        Integer status = classicService.deleteClassic(id);
    }
}
