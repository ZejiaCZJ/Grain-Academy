package com.grainacademy.backend.courseservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("edu_course")
@Schema(name = "Course", description = "课程")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "课程讲师ID")
    private String teacherId;

    @Schema(description = "课程专业ID")
    private String subjectId;

    @Schema(description = "课程专业父级ID")
    private String subjectParentId;

    @Schema(description = "课程标题")
    private String title;

    @Schema(description = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @Schema(description = "总课时")
    private Integer lessonNum;

    @Schema(description = "课程封面图片路径")
    private String cover;

    @Schema(description = "销售数量")
    private Long buyCount;

    @Schema(description = "浏览数量")
    private Long viewCount;

    @Schema(description = "乐观锁")
    private Long version;

    @Schema(description = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Byte isDeleted;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
