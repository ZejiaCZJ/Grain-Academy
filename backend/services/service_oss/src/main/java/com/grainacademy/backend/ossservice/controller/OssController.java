package com.grainacademy.backend.ossservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.ossservice.service.OssService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ossservice/file")
@Tag(name = "OssController", description = "The api manages oss files")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping()
    public Result uploadOssFile(MultipartFile file){

        String url = ossService.uploadProfileAvatar(file);

        return Result.succeed().data("records", url);
    }

}
