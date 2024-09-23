package com.grainacademy.backend.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.grainacademy.backend.eduservice.entity.Teacher;
import com.grainacademy.backend.eduservice.service.ITeacherService;
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
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    //Search all teachers in the teacher table
    @GetMapping("findAll")
    public List<Teacher> findAllTeachers(){
        List<Teacher> teacherList = teacherService.list((Wrapper<Teacher>) null);
        System.out.println("Got the following result" + teacherList);
        return teacherList;
    }

    //Delete a specific teacher from the table
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id)
    {
        return teacherService.removeById(id);
    }


}
