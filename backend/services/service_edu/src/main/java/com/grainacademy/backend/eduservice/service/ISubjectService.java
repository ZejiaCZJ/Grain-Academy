package com.grainacademy.backend.eduservice.service;

import com.grainacademy.backend.eduservice.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-04
 */
public interface ISubjectService extends IService<Subject> {

    void saveSubject(MultipartFile file, ISubjectService subjectService);
}
