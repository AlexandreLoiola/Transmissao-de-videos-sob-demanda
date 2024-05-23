package com.AlexandreLoiola.OnDemandVideoStreaming.rest.controller;

import com.AlexandreLoiola.OnDemandVideoStreaming.model.VideoModel;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.VideoDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.VideoForm;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(name = "/{title}")
    public ResponseEntity<VideoDto> getVideoByTitle(@PathVariable String title) {
        VideoDto videoDto = videoService.getVideoDtoByTitle(title);
        return ResponseEntity.ok().body(videoDto);
    }

    @PostMapping
    public ResponseEntity<VideoDto> insertVideo(@Valid @RequestBody VideoForm videoForm) {
        VideoDto videoDto = videoService.insertVideo(videoForm);
        return ResponseEntity.ok().body(videoDto);
    }

    @PutMapping(name = "/{title}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable String title, @Valid @RequestBody VideoForm videoForm) {
        VideoDto videoDto = videoService.updateVideo(title, videoForm);
        return ResponseEntity.ok().body(videoDto);
    }

    @PutMapping(name = "/{title}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String title) {
        videoService.deleteVideo(title);
        return ResponseEntity.ok().build();
    }
}
