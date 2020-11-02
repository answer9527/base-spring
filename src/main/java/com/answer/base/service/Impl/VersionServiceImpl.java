package com.answer.base.service.Impl;

import com.answer.base.dao.VersionMapper;
import com.answer.base.entity.Version;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionMapper versionMapper;
    @Override
    public List<Version> getVersionList() {
        return versionMapper.getVersionList();
    }

    @Override
    public void insertVersion(Version version) {
        Boolean bool = versionMapper.insertVersion(version);
        if(!bool){
            throw  new ParameterException(50001);
        }
    }

    @Override
    public void updateVersion(Version version) {
        Boolean bool = versionMapper.updateVersion(version);
        if(!bool){
            throw  new ParameterException(50001);
        }
    }

    @Override
    public Version getLatestVersion() {
        Optional<Version> versionOptional = Optional.ofNullable(versionMapper.getLatestVersion());
        Version version = versionOptional.orElseThrow(()->new ParameterException(50004));
        return version;
    }
}
