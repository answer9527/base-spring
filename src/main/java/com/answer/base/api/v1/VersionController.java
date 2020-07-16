package com.answer.base.api.v1;


import com.answer.base.entity.Version;
import com.answer.base.service.VersionService;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/version")
public class VersionController {
    @Autowired
    private VersionService versionService;
    @GetMapping("/list")
    public Msg getVersionList(){
        List<Version> versionList = versionService.getVersionList();
        return ResultUtil.success(versionList);
    }
}
