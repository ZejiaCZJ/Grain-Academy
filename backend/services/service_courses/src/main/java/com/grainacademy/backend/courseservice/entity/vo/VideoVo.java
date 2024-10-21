package com.grainacademy.backend.courseservice.entity.vo;

import lombok.Data;

@Data
public class VideoVo {
    private String id;

    private String courseId;

    private String chapterId;

    private String title;
}
