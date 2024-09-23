package com.grainacademy.backend.eduservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Teacher", description = "讲师查询封装")
public class TeacherVo {

    @Schema(description = "讲师姓名,模糊查询")
    private String name;

    @Schema(description = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @Schema(description = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @Schema(description = "查询结束时间", example = "2019-01-01 10:10:10")
    private String end;


}
