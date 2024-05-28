package com.AlexandreLoiola.OnDemandVideoStreaming.rest.controller;

import com.AlexandreLoiola.OnDemandVideoStreaming.mapper.VideoMapper;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.VideoDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Set<VideoDto>> getAllVideoDto() {
        Set<VideoDto> videoDtos = videoService.getAllVideoDto();
        return ResponseEntity.ok().body(videoDtos);
    }

    @GetMapping("/{title}")
    public ResponseEntity<VideoDto> getVideoByTitle(@PathVariable String title) {
        VideoDto videoDto = videoService.getVideoDtoByTitle(title);
        return ResponseEntity.ok().body(videoDto);
    }

    @PostMapping(path = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<VideoDto> insertVideo(
            @RequestPart("video") MultipartFile video,
            @RequestPart("thumbnail") MultipartFile thumbnail,
            @RequestParam("videoForm") @Valid VideoForm videoForm) throws IOException
    {
        VideoDto videoDto = videoService.insertVideo(video, thumbnail, videoForm);
        return ResponseEntity.ok().body(videoDto);
    }

    @PutMapping("/{title}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable String title, @Valid @RequestBody VideoForm videoForm) {
        VideoDto videoDto = videoService.updateVideo(title, videoForm);
        return ResponseEntity.ok().body(videoDto);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String title) {
        videoService.deleteVideo(title);
        return ResponseEntity.ok().build();
    }
}