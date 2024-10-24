package com.grainacademy.backend.courseservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.courseservice.entity.Video;
import com.grainacademy.backend.courseservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@RestController
@RequestMapping("/coursesservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping
    private Result addNewVideoToChapterById(@RequestBody Video video){
        if(videoService.save(video))
            return Result.succeed();
        return Result.failed();
    }

    @DeleteMapping("/{id}")
    private Result deleteVideoById(@PathVariable String id){

        if(videoService.removeById(id)){
            return Result.succeed();
        }

        return Result.failed();
    }

    @PutMapping
    private Result updateVideo(@RequestBody Video video){
        if(videoService.updateById(video)){
            return Result.succeed();
        }
        return Result.failed();
    }

    @GetMapping("/{id}")
    private Result getVideoById(@PathVariable String id){
        if(id != null && !id.isEmpty()){
            Video video = videoService.getById(id);

            return Result.succeed().data("records", video);
        }
        return Result.failed();
    }




}
