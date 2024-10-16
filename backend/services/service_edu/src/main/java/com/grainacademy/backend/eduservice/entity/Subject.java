package com.grainacademy.backend.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-04
 */
@Getter
@Setter
@TableName("edu_subject")
@Schema(name = "Subject", description = "课程科目")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程类别ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "类别名称")
    private String title;

    @Schema(description = "父ID")
    private String parentId;

    @Schema(description = "排序字段")
    private Integer sort;

    @Schema(description = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
