package com.grainacademy.backend.courseservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grainacademy.backend.courseservice.entity.Chapter;
import com.grainacademy.backend.courseservice.entity.Video;
import com.grainacademy.backend.courseservice.entity.vo.ChapterVo;
import com.grainacademy.backend.courseservice.entity.vo.VideoVo;
import com.grainacademy.backend.courseservice.mapper.ChapterMapper;
import com.grainacademy.backend.courseservice.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grainacademy.backend.courseservice.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterAndVideoById(String id) {
        List<ChapterVo> chapters = new ArrayList<>();

        // Get all chapter
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        List<Chapter> allChapters = baseMapper.selectList(chapterQueryWrapper);

        // Get all chapter videos
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        List<Video> allVideos = videoService.list(videoQueryWrapper);

        // Package the list of ChapterVO with chapters
        Map<String, ChapterVo> chapterVoMap = new HashMap<>();
        for (Chapter chapter : allChapters){
            ChapterVo vo = new ChapterVo();
            BeanUtils.copyProperties(chapter, vo);
            chapters.add(vo);
            chapterVoMap.put(chapter.getId(), vo);
        }


        // Package the list of ChapterVO with chapters' videos
        for (Video video : allVideos){
            ChapterVo vo = chapterVoMap.get(video.getChapterId());
            if(vo.getChildren() == null)
                vo.setChildren(new ArrayList<>());
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video, videoVo);
            vo.getChildren().add(videoVo);
        }
        return chapters;
    }

    @Override
    public void addNewChapter(Chapter chapter) {
        baseMapper.insert(chapter);
    }

    @Override
    public void updateChapter(String chapterId, Chapter chapter) {
        //chapter.setId(chapterId);
        System.out.println(chapter);
    }
}
