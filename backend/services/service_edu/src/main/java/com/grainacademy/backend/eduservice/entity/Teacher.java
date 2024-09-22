package com.grainacademy.backend.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-09-22
 */
@Getter
@Setter
@TableName("edu_teacher")
@Schema(name = "Teacher", description = "讲师")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "讲师ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "讲师姓名")
    private String name;

    @Schema(description = "讲师简介")
    private String intro;

    @Schema(description = "讲师资历,一句话说明讲师")
    private String career;

    @Schema(description = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @Schema(description = "讲师头像")
    private String avatar;

    @Schema(description = "排序")
    private Integer sort;

    @TableLogic
    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Byte isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date gmtModified;
}
