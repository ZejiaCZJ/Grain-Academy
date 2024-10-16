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
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.saveCourseInfo(courseInfoVo);


        return Result.succeed();
    }


}
