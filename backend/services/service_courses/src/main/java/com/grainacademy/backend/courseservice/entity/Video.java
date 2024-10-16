package com.grainacademy.backend.courseservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Getter
@Setter
@TableName("edu_video")
@Schema(name = "Video", description = "课程视频")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "视频ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "课程ID")
    private String courseId;

    @Schema(description = "章节ID")
    private String chapterId;

    @Schema(description = "节点名称")
    private String title;

    @Schema(description = "云端视频资源")
    private String videoSourceId;

    @Schema(description = "原始文件名称")
    private String videoOriginalName;

    @Schema(description = "排序字段")
    private Integer sort;

    @Schema(description = "播放次数")
    private Long playCount;

    @Schema(description = "是否可以试听：0收费 1免费")
    private Byte isFree;

    @Schema(description = "视频时长（秒）")
    private Double duration;

    @Schema(description = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @Schema(description = "视频源文件大小（字节）")
    private Long size;

    @Schema(description = "乐观锁")
    private Long version;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
