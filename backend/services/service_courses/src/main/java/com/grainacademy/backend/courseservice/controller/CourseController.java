package com.grainacademy.backend.courseservice.controller;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.courseservice.entity.vo.CourseInfoVo;
import com.grainacademy.backend.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@RestController
@RequestMapping("/coursesservice/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);


        return Result.succeed().data("records", id);
    }

    @GetMapping("/{id}")
    public Result getCourseInfo(@PathVariable String id){

        CourseInfoVo vo = courseService.getCourseInfo(id);

        return Result.succeed().data("records", vo);
    }

    @PutMapping
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.updateCourseInfo(courseInfoVo);

        return Result.succeed().data("records", id);
    }

}
