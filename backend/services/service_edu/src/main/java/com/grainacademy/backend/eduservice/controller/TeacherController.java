package com.grainacademy.backend.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grainacademy.backend.eduservice.entity.vo.TeacherVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.eduservice.entity.Teacher;
import com.grainacademy.backend.eduservice.service.ITeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    @GetMapping("")
    @Operation(summary="selectAllTeachers", description = "Search all teachers")
    @ApiResponse(description = "data field: return all undeleted teachers")
    public Result selectAllTeachers(){
        List<Teacher> teacherList = teacherService.list((Wrapper<Teacher>) null);

        return Result.succeed().data("records", teacherList);

    }

    //Delete a specific teacher from the table
    @DeleteMapping("{id}")
    @Operation(summary="removeTeacherById", description = "Delete a teacher by id")
    @Parameter(name = "id", description = "The id that need to be deleted")
    @ApiResponse(description = "return the corresponding Result code on the deletion")
    public Result removeTeacherById(@PathVariable String id)
    {
        boolean flag = teacherService.removeById(id);

        if (flag)
            return Result.succeed();
        else
            return Result.failed().message("Id not found in database");
    }

    //Retrieve pages of teachers
    @GetMapping("/{current}/{limit}")
    @Operation(summary="selectTeacherByPage", description = "Retrieve the desired page of teachers")
    @Parameter(name = "current", description = "the page number")
    @Parameter(name = "limit", description = "the number of teachers in this page")
    @ApiResponse(description="data field: return teachers in the given page number")
    public Result selectTeacherByPage(@PathVariable long current, @PathVariable long limit){
        Page<Teacher> pageTeacher = new Page<>(current, limit);
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();

        if (records == null)
            return Result.failed().message("failed to retrieve the teacher list in the required page");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("count", total);
        data.put("records", records);

        return Result.succeed().data(data);
    }

    //Retrieve pages of teachers with dynamic conditions
    //@PostMapping("/condition/{current}/{limit}")
    @PostMapping("/{current}/{limit}")
    @Operation(summary="selectTeacherByPageAndCondition", description = "Retrieve the desired page of teachers that satisfy all conditions")
    @Parameter(name = "current", description = "the page number")
    @Parameter(name = "limit", description = "the number of teachers in this page")
    @ApiResponse(description="data field: return teachers in the given page number")
    public Result selectTeacherByPageAndCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherVo teachervo){
        Page<Teacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();

        String name = teachervo.getName();
        Integer level = teachervo.getLevel();
        String begin = teachervo.getBegin();
        String end = teachervo.getEnd();

        if (StringUtils.hasLength(name))
            teacherQueryWrapper.like("name", name);

        if (level != null && level >= 0)
            teacherQueryWrapper.eq("level", level);
        if (StringUtils.hasLength(begin))
            teacherQueryWrapper.ge("gmt_create", begin);
        if (StringUtils.hasLength(end))
            teacherQueryWrapper.le("gmt_create", end);


        teacherService.page(pageTeacher, teacherQueryWrapper);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();

        return Result.succeed().data("total", total).data("records", records);
    }

    @PostMapping("")
    @Operation(summary="addTeacher", description = "Add a teacher to the table")
    @ApiResponse(description="status of the addition")
    public Result addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(!save)
            return Result.failed().message("failed to add a new teacher");
        return Result.succeed();
    }

    @GetMapping("{id}")
    @Operation(summary="searchTeacherById", description = "Search the teacher by the given id")
    @Parameter(name = "id", description = "the teacher id")
    @ApiResponse(description="data field: the teacher with the given id")
    public Result searchTeacherById(@PathVariable Integer id){
        Teacher teacher = teacherService.getById(id);
        return Result.succeed().data("records", teacher);
    }

    @PutMapping("")
    @Operation(summary="updateTeacher", description = "update the given id")
    @Parameter(name = "id", description = "the teacher")
    @ApiResponse(description="status of the update")
    public Result updateTeacher(@RequestBody Teacher teacher){

        boolean flag = teacherService.updateById(teacher);
        if (flag)
            return Result.succeed();
        else
            return Result.failed().message("failed to update the given teacher");

    }

}
