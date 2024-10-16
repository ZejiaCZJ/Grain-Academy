package com.grainacademy.backend.courseservice.service.impl;

import com.grainacademy.backend.courseservice.entity.Video;
import com.grainacademy.backend.courseservice.mapper.VideoMapper;
import com.grainacademy.backend.courseservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ZejiaCai
 * @since 2024-10-15
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
