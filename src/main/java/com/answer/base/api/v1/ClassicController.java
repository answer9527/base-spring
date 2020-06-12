package com.answer.base.api.v1;

import com.answer.base.entity.Classic;
import com.answer.base.service.ClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/classic")
public class ClassicController {
    @Autowired
    private ClassicService classicService;
    @GetMapping("/latest")
    public Classic getRecommendLatest(){
        return classicService.getRecommendLatest();
    }

    @GetMapping("/{id}/previous")
    public Classic getRecommendPrevious(@PathVariable Integer id){
        return classicService.getRecommendPrevious(id);
    }

}
