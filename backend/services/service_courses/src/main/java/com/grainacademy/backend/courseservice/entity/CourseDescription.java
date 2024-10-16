package com.grainacademy.backend.courseservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Getter
@Setter
@TableName("edu_course_description")
@Schema(name = "CourseDescription", description = "课程简介")
public class CourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
      @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @Schema(description = "课程简介")
    private String description;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
