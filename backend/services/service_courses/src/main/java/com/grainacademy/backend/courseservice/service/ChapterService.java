package com.grainacademy.backend.courseservice.service;

import com.grainacademy.backend.courseservice.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grainacademy.backend.courseservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterAndVideoById(String id);

    void addNewChapter(Chapter chapter);

    void updateChapter(String chapterId, Chapter chapter);
}
