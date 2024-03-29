package com.answer.base.api.v1;


import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.entity.Version;
import com.answer.base.service.VersionService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/insert")
    @ScopeLevel(9)
    public Msg insertVersion(@RequestBody @Validated Version version){
        versionService.insertVersion(version);
        return  ResultUtil.success("新增成功");
    }

    @PostMapping("/update")
    public Msg updateVersion(@RequestBody @Validated Version version){
        versionService.updateVersion(version);
        return ResultUtil.success("更新成功！");
    }
    @PostMapping("/latest")
    public Msg getLatestVersion(){
        Version version = versionService.getLatestVersion();
        return ResultUtil.success(version);
    }


}

