package com.grainacademy.backend.courseservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.courseservice.entity.Chapter;
import com.grainacademy.backend.courseservice.entity.vo.ChapterVo;
import com.grainacademy.backend.courseservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

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
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/all/{id}")
    private Result getChapterAndVideoById(@PathVariable String id){

        List<ChapterVo> chapters = chapterService.getChapterAndVideoById(id);

        return Result.succeed().data("records", chapters);
    }

    @GetMapping("/{id}")
    private Result getChapterInfo(@PathVariable String id){

        Chapter chapter = chapterService.getById(id);

        return Result.succeed().data("records", chapter);
    }

    @PostMapping()
    private Result addNewChapter(@RequestBody Chapter chapter){

        if(chapter != null && chapter.getCourseId() != null && chapter.getTitle() != null){
            chapterService.addNewChapter(chapter);
            return Result.succeed();
        }
        return Result.failed();
    }

    @PutMapping
    private Result updateChapterInfo(@RequestBody Chapter chapter){
        if(chapter.getId() != null){
            chapterService.updateById(chapter);
            return Result.succeed();
        }

        return Result.failed();
    }

    @DeleteMapping("/{id}")
    private Result deleteChapterById(@PathVariable String id){
        chapterService.removeById(id);

        return Result.succeed();
    }



}
