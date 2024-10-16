package com.grainacademy.backend.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.grainacademy.backend.eduservice.entity.Subject;
import com.grainacademy.backend.eduservice.entity.excel.ExcelSubjectData;
import com.grainacademy.backend.eduservice.listener.SubjectExcelListener;
import com.grainacademy.backend.eduservice.mapper.SubjectMapper;
import com.grainacademy.backend.eduservice.service.ISubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-04
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Override
    public void saveSubject(MultipartFile file, ISubjectService subjectService) {
        try{
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
