package com.grainacademy.backend.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.eduservice.entity.Subject;
import com.grainacademy.backend.eduservice.entity.vo.SubjectVo;
import com.grainacademy.backend.eduservice.service.ISubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-04
 */
@RestController
@RequestMapping("/eduservice/subject")
@Tag(name = "SubjectController", description = "The api manages subjects information")
@CrossOrigin
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @PostMapping
    @Operation(summary="addSubject", description = "Add subjects to the dataset")
    @Parameter(name = "file", description = "The txt file that contains subject information")
    @ApiResponse(description="status of the addition")
    public Result addSubject(MultipartFile file){

        subjectService.saveSubject(file, subjectService);

        return Result.succeed();
    }

//    @GetMapping
//    @Operation(summary="getSubjects", description = "get subjects from the dataset")
//    @ApiResponse(description="status of the query")
//    public Result getSubjects(){
//        //Get the class one subjects first
//        QueryWrapper<Subject> classOneQueryWrapper = new QueryWrapper<>();
//        classOneQueryWrapper.eq("parent_id", 0);
//        List<Subject> classOneSubjects = subjectService.list(classOneQueryWrapper);
//
//        List<SubjectVo> subjectLists = new ArrayList<>();
//
//        for(int i = 0; i < classOneSubjects.size(); i++) {
//            Subject currentClassOneSubject = classOneSubjects.get(i);
//            subjectLists.add(new SubjectVo(currentClassOneSubject.getId(), currentClassOneSubject.getTitle()));
//
//            //Find all childrens of the current class one subject
//            QueryWrapper<Subject> classTwoQueryWrapper = new QueryWrapper<>();
//            classTwoQueryWrapper.eq("parent_id", currentClassOneSubject.getId());
//            List<Subject> classTwoSubjects = subjectService.list(classTwoQueryWrapper);
//
//            //Add all childrens to the current class one subject
//            for(int j = 0; j < classTwoSubjects.size();j++){
//                Subject currentClassTwoSubject = classTwoSubjects.get(j);
//                subjectLists.get(i).getChildren().add(new SubjectVo(currentClassTwoSubject.getId(), currentClassTwoSubject.getTitle()));
//            }
//        }
//
//        return Result.succeed().data("records", subjectLists);
//    }

    @GetMapping
    @Operation(summary="getSubjects", description = "get subjects from the dataset")
    @ApiResponse(description="status of the query")
    public Result getSubjects(){
        // Get all subjects at once
        List<Subject> allSubjects = subjectService.list();

        // returning list
        List<SubjectVo> subjectLists = new ArrayList<>();

        // Class one subjects
        Map<String, SubjectVo> classOneMap = new HashMap<>();

        // Add Class 1 subjects
        for (Subject subject : allSubjects) {
            if (subject.getParentId().equals("0")) {
                SubjectVo classOneVo = new SubjectVo(subject.getId(), subject.getTitle());
                classOneVo.setChildren(new ArrayList<>());
                subjectLists.add(classOneVo);
                classOneMap.put(subject.getId(), classOneVo);
            }
        }

        // Add Class 2 subjects
        for (Subject subject : allSubjects) {
            if (!subject.getParentId().equals("0")) {
                // Get corresponding class one subject
                SubjectVo classOneVo = classOneMap.get(subject.getParentId());
                if (classOneVo != null) {
                    if(classOneVo.getChildren() == null){
                      classOneVo.setChildren(new ArrayList<>());
                    }
                    classOneVo.getChildren().add(new SubjectVo(subject.getId(), subject.getTitle()));
                }
            }
        }

        return Result.succeed().data("records", subjectLists);
    }



}
