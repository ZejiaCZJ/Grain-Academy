package com.grainacademy.backend.courseservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.courseservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@RestController
@RequestMapping("/coursesservice/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{id}")
    private Result getChaptersById(@PathVariable String id){
        return Result.succeed();
    }
}
