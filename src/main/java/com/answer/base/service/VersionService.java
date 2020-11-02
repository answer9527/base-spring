package com.answer.base.service;

import com.answer.base.entity.Version;

import java.util.List;

public interface VersionService {
    List<Version> getVersionList();
    void insertVersion(Version version);
    void updateVersion(Version version);
    Version getLatestVersion();
}
