package com.grainacademy.backend.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadProfileAvatar(MultipartFile file, String type);

    Boolean deleteOssFile(String url);
}
