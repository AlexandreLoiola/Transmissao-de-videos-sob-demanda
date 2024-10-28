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
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    private final S3Service s3Service;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper, S3Service s3Service) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.s3Service = s3Service;
    }

    public VideoModel findVideoModelByTitle(String title) {
        log.info("Searching for video by title: {}", title);
        return videoRepository.findByTitleAndIsActiveTrue(title)
                .orElseThrow(() -> {
                    log.error("Video not found with title: {}", title);
                    return new VideoNotFoundException(
                            String.format("The title ‘%s’ was not found", title)
                    );
                });
    }

    private boolean isVideoTitleExists(String title) {
        log.info("Checking if video title exists: {}", title);
        try {
            findVideoModelByTitle(title);
            return true;
        } catch (VideoNotFoundException e) {
            log.info("Video title not found: {}", title);
            return false;
        }
    }

    public Set<VideoDto> getAllVideoDtoFromPlaylist(String playlist) {
        log.info("Retrieving videos from playlist: {}", playlist);
        Set<VideoModel> videoModels = videoRepository.findByFolderAndIsActiveTrue(playlist);
        if (videoModels.isEmpty()) {
            log.error("No active videos found in playlist: {}", playlist);
            throw new VideoNotFoundException("No active video was found");
        }
        log.info("Videos found: {}", videoModels.size());
        return videoModels.stream()
                .map(this::modelToDtoWithUrls)
                .collect(Collectors.toSet());
    }

    public VideoDto getVideoDtoByTitle(String title) {
        VideoModel videoModel = findVideoModelByTitle(title);
        return modelToDtoWithUrls(videoModel);
    }

    public VideoDto modelToDtoWithUrls(VideoModel videoModel) {
        log.info("Converting model to DTO with URLs for video: {}", videoModel.getTitle());
        VideoDto videoDto = videoMapper.modelToDto(videoModel);
        String videoKey = "videos/xandin/" + videoModel.getFolder() + "/" + videoModel.getTitle();
        String thumbnailKey = "thumbs/xandin/" + videoModel.getFolder() + "/" + videoModel.getTitle();
        URL videoUrl = s3Service.generateTempUrl(videoKey, 30);
        URL thumbnailUrl = s3Service.generateTempUrl(thumbnailKey, 30);
        videoDto.setVideoUrl(videoUrl);
        videoDto.setThumbnailUrl(thumbnailUrl);
        return videoDto;
    }

    public void uploadVideoAndThumb(String id, String title, String folder, MultipartFile video, MultipartFile thumb) throws IOException {
        log.info("Uploading video and thumbnail for ID: {}", id);
        String videoFolder = "videos/xandin/" + folder;
        String thumbFolder = "thumbs/xandin/" + folder;
        s3Service.uploadFile(id, title, videoFolder, video);
        s3Service.uploadFile(id, title, thumbFolder, thumb);
    }

    @Transactional
    public VideoDto insertVideo(MultipartFile video, MultipartFile thumb, VideoForm videoForm) throws IOException {
        log.info("Inserting video: {}", videoForm.getTitle());
        if (isVideoTitleExists(videoForm.getTitle())) {
            log.error("Video already registered: {}", videoForm.getTitle());
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
            videoModel = videoRepository.save(videoModel);
            uploadVideoAndThumb((videoModel.getId()).toString(), videoModel.getTitle(), videoModel.getFolder(), video, thumb);
            log.info("Video inserted successfully: {}", videoForm.getTitle());
            return videoMapper.modelToDto(videoModel);
        } catch (DataIntegrityViolationException err) {
            log.error("Failed to register the video: {}. Error: {}", videoForm.getTitle(), err.getMessage());
            throw new VideoNotFoundException(
                    String.format("Failed to register the video ‘%s’. Check if the data is correct", videoForm.getTitle()));
        }
    }

    @Transactional
    public VideoDto updateVideo(String title, VideoForm videoForm) {
        log.info("Updating video: {}", title);
        VideoModel videoModel = findVideoModelByTitle(title);
        try {
            videoModel.setTitle(videoForm.getTitle());
            videoModel.setDescription(videoForm.getDescription());
            videoRepository.save(videoModel);
            log.info("Video updated successfully: {}", title);
            return videoMapper.modelToDto(videoModel);
        } catch (DataIntegrityViolationException err) {
            log.error("Failed to update the video: {}. Error: {}", title, err.getMessage());
            throw new VideoUpdateException(String.format("Failed to update the video ‘%s’. Check if the data is correct", title));
        }
    }

    @Transactional
    public void deleteVideo(String title) {
        log.info("Deleting video: {}", title);
        VideoModel videoModel = findVideoModelByTitle(title);
        try {
            videoModel.setActive(false);
            videoModel.setUpdatedAt(new Date());
            videoRepository.save(videoModel);
            log.info("Video deleted successfully: {}", title);
        } catch (DataIntegrityViolationException err) {
            log.error("Failed to delete the video: {}. Error: {}", title, err.getMessage());
            throw new VideoUpdateException(String.format("Failed to delete the video ‘%s’. Check if the data is correct", title));
        }
    }
}