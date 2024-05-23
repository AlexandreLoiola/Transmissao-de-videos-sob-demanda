package com.AlexandreLoiola.OnDemandVideoStreaming.service;

import com.AlexandreLoiola.OnDemandVideoStreaming.mapper.VideoMapper;
import com.AlexandreLoiola.OnDemandVideoStreaming.model.VideoModel;
import com.AlexandreLoiola.OnDemandVideoStreaming.repository.VideoRepository;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.VideoDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video.VideoInsertException;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video.VideoNotFoundException;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video.VideoUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    public VideoModel findVideoModelByTitle(String title) {
        return videoRepository.findByTitleAndIsActiveTrue(title)
                .orElseThrow(() -> new VideoNotFoundException(
                        String.format("The title ‘%s’ was not found", title)
                ));
    }

    private boolean isVideoTitleExists(String title) {
        try {
            findVideoModelByTitle(title);
            return true;
        } catch (VideoNotFoundException e) {
            return false;
        }
        }

    public Set<VideoDto> getAllVideoDto() {
        Set<VideoModel> videoModels = videoRepository.findByIsActiveTrue();
        if (videoModels.isEmpty()) {
            throw new VideoNotFoundException("No active video was found");
        }
        return videoMapper.setModelToSetDto(videoModels);
    }

    public VideoDto getVideoDtoByTitle(String title) {
        VideoModel videoModel = findVideoModelByTitle(title);
        return videoMapper.modelToDto(videoModel);
    }

    @Transactional
    public VideoDto insertVideo(VideoForm videoForm) {
        if (isVideoTitleExists(videoForm.getTitle())) {
            throw new VideoInsertException(String.format("The video ‘%s’ is already registered", videoForm.getTitle()));
        }
        try {
            VideoModel videoModel = videoMapper.formToModel(videoForm);
            Date date = new Date();
            videoModel.setUploadedAt(date);
            videoModel.setCreatedAt(date);
            videoModel.setUpdatedAt(date);
            videoModel.setActive(true);
            videoModel.setVersion(1);
            videoRepository.save(videoModel);
            return videoMapper.modelToDto(videoModel);
        } catch (DataIntegrityViolationException err) {
            throw new VideoNotFoundException(String.format("Failed to register the video ‘%s’. Check if the data is correct", videoForm.getTitle()));
        }
    }

    @Transactional
    public VideoDto updateVideo(String title ,VideoForm videoForm) {
        VideoModel videoModel = findVideoModelByTitle(title);
        try {
            videoModel.setTitle(videoForm.getTitle());
            videoModel.setDescription(videoForm.getDescription());
            videoModel.setUrl(videoForm.getUrl());
            videoRepository.save(videoModel);
            return videoMapper.modelToDto(videoModel);
        } catch (DataIntegrityViolationException err) {
            throw new VideoUpdateException(String.format("Failed to update the video ‘%s’. Check if the data is correct", title));
        }
    }

    @Transactional
    public void deleteVideo(String title) {
        VideoModel videoModel = findVideoModelByTitle(title);
        try {
            videoModel.setActive(false);
            videoModel.setUpdatedAt(new Date());
            videoRepository.save(videoModel);
        } catch (DataIntegrityViolationException err) {
            throw new VideoUpdateException(String.format("Failed to delete the video ‘%s’. Check if the data is correct", title));
        }
    }
}