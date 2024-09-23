package com.grainacademy.backend.eduservice;

import com.grainacademy.backend.eduservice.entity.Teacher;
import com.grainacademy.backend.eduservice.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EduServiceApplicationTests {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void insertTeacher()
    {
        Teacher teacher = new Teacher();
        teacher.setId("2");
        teacher.setAvatar("https://grainacademy/teacher/avatar/2");
        teacher.setIntro("至尊导师");
        teacher.setCareer("至尊导师");
        teacher.setLevel(1);
        teacher.setName("Zejia");
        System.out.println("Hello world");
        teacherMapper.insert(teacher);
    }

}
