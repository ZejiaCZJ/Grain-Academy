package com.grainacademy.backend.ossservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.ossservice.service.OssService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ossservice/file")
@Tag(name = "OssController", description = "The api manages oss files")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/{type}")
    public Result uploadOssFile(@PathVariable String type, MultipartFile file){

        String url = ossService.uploadProfileAvatar(file, type);

        return Result.succeed().data("records", url);
    }

    @DeleteMapping("")
    public Result deleteOssFile(@RequestBody String url){

        ossService.deleteOssFile(url);

        return Result.succeed().data("records", url);
    }

}
