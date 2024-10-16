package com.grainacademy.backend.courseservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Getter
@Setter
@TableName("edu_chapter")
@Schema(name = "Chapter", description = "课程")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "章节ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "课程ID")
    private String courseId;

    @Schema(description = "章节名称")
    private String title;

    @Schema(description = "显示排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date gmtModified;
}
