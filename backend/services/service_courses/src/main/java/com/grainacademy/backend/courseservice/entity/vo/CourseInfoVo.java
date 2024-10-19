package com.grainacademy.backend.courseservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "Course Information Vo", description = "This contains a course's display information")
public class CourseInfoVo {
    @Schema(description = "Course Id")
    private String id;

    @Schema(description = "Teacher Id")
    private String teacherId;

    @Schema(description = "Subject Id")
    private String subjectId;

    @Schema(description = "Subject Parent Id")
    private String subjectParentId;

    @Schema(description = "Course Title")
    private String title;

    @Schema(description = "Price of the course, 0 stands for free")
    private BigDecimal price;

    @Schema(description = "Lesson Number")
    private Integer lessonNum;

    @Schema(description = "Course Cover Picture's URL")
    private String cover;

    @Schema(description = "Course description")
    private String description;
}
