package com.grainacademy.backend.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grainacademy.backend.eduservice.entity.Subject;
import com.grainacademy.backend.eduservice.entity.excel.ExcelSubjectData;
import com.grainacademy.backend.eduservice.service.ISubjectService;
import com.grainacademy.backend.servicebase.exceptions.BackEndException;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public ISubjectService subjectService;

    public SubjectExcelListener(ISubjectService subjectService){
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if(excelSubjectData == null)
            throw new BackEndException(20001, "File is empty");


        Subject existedClassOneSubject = this.existClassOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        if(existedClassOneSubject == null){ //Add this class one subject to database
            existedClassOneSubject = new Subject();

            existedClassOneSubject.setParentId("0");
            existedClassOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            subjectService.save(existedClassOneSubject);
        }

        String pid = existedClassOneSubject.getId();

        Subject existedClassTwoSubject = this.existClassTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);
        if(existedClassTwoSubject == null){ //Add this class one subject to database
            existedClassTwoSubject = new Subject();

            existedClassTwoSubject.setParentId(pid);
            existedClassTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());

            subjectService.save(existedClassTwoSubject);
        }
    }

    //Method: make sure class 1 subject cannot be duplicated
    private Subject existClassOneSubject(ISubjectService subjectService, String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        Subject oneSubject = subjectService.getOne(wrapper);

        return oneSubject;
    }


    //Method: make sure class 2 subject cannot be duplicated
    private Subject existClassTwoSubject(ISubjectService subjectService, String name, String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        Subject twoSubject = subjectService.getOne(wrapper);

        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
