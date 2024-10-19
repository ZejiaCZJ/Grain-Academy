package com.grainacademy.backend.courseservice.service;

import com.grainacademy.backend.courseservice.entity.vo.CourseInfoVo;
import com.grainacademy.backend.courseservice.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
public interface CourseService extends IService<Course> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
