package com.grainacademy.backend.courseservice.service.impl;

import com.grainacademy.backend.courseservice.entity.CourseDescription;
import com.grainacademy.backend.courseservice.entity.vo.CourseInfoVo;
import com.grainacademy.backend.courseservice.entity.Course;
import com.grainacademy.backend.courseservice.mapper.CourseMapper;
import com.grainacademy.backend.courseservice.service.CourseDescriptionService;
import com.grainacademy.backend.courseservice.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grainacademy.backend.servicebase.exceptions.BackEndException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //Add a Course record
        Course course = new Course();

        //Deep copy
        BeanUtils.copyProperties(courseInfoVo, course);// This performs a relatively deep copy
        course.setStatus("Draft");

        int insert = baseMapper.insert(course);

        if(insert == 0) {
            throw new BackEndException(20001, "Fail to save the course information");
        }

        //Get the course id
        String cid = course.getId();

        //Add a Course Description record
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String id) {

        Course course = baseMapper.selectById(id);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        CourseDescription courseDescription = courseDescriptionService.getById(id);

        courseInfoVo.setDescription(courseDescription.getDescription());



        return courseInfoVo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        //Add a Course record
        Course course = new Course();

        //Deep copy
        BeanUtils.copyProperties(courseInfoVo, course);// This performs a relatively deep copy
        course.setStatus("Draft");

        baseMapper.updateById(course);

        //Update course description
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(courseInfoVo.getId());
        courseDescriptionService.updateById(courseDescription);



        return course.getId();
    }
}
