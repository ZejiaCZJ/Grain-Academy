package com.grainacademy.backend.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.grainacademy.backend.commonutils.*;
import com.grainacademy.backend.eduservice.entity.Teacher;
import com.grainacademy.backend.eduservice.service.ITeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-09-22
 */

@RestController
@RequestMapping("/eduservice/teacher")
@Tag(name = "TeacherController", description = "操作老师信息的接口")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    //Search all teachers in the teacher table
    @GetMapping("findAll")
    @Operation(summary="findAll", description = "Search all teachers")
    @ApiResponse(description = "return all undeleted teachers")
    public Result findAllTeachers(){


        List<Teacher> teacherList = teacherService.list((Wrapper<Teacher>) null);

        return Result.succeed().data("items", teacherList);
    }

    //Delete a specific teacher from the table
    @DeleteMapping("{id}")
    @Operation(summary="removeTeacher", description = "Delete a teacher by id")
    @Parameter(name = "id", description = "The id that need to be deleted")
    @ApiResponse(description = "return true/false on the deletion")
    public Result removeTeacher(@PathVariable String id)
    {
        boolean flag = teacherService.removeById(id);
        if(flag)
            return Result.succeed();
        else
            return Result.failed();
    }


}
