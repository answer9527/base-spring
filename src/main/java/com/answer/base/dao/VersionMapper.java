package com.answer.base.dao;

import com.answer.base.entity.Version;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VersionMapper {
    List<Version> getVersionList();
    Boolean insertVersion(Version version);
}
