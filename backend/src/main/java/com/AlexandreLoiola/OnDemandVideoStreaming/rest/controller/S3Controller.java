package com.AlexandreLoiola.OnDemandVideoStreaming.rest.controller;

import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.FolderDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.CreateFolderForm;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.S3Service;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<S3ObjectSummary>> listFiles() {
        List<S3ObjectSummary> files = s3Service.listFiles();
        return ResponseEntity.ok(files);
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        s3Service.uploadFile(file.getOriginalFilename(), file);
        return ResponseEntity.ok().body("File uploaded");
    }

    @PostMapping(path = "/createfolder")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolderForm createFolderForm) {
        s3Service.createFolder(createFolderForm);
        return ResponseEntity.ok().body("The folder has been created");
    }

    @GetMapping(path = "/listfolder")
    public ResponseEntity<List<FolderDto>> listFolder() {
        List<FolderDto> folderDtos = s3Service.listFolders();
        return ResponseEntity.ok().body(folderDtos);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downlaodFile(@PathVariable String fileName) {
        InputStreamResource file = s3Service.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }

    @GetMapping("/view/image/{fileName}")
    public ResponseEntity<InputStreamResource> viewImageFile(@PathVariable String fileName) {
        InputStreamResource file = s3Service.viewImageFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+fileName+"\"")
                .body(file);
    }

    @GetMapping("/view/video/{fileName}")
    public ResponseEntity<InputStreamResource> viewVideoFile(@PathVariable String fileName) {
        InputStreamResource file = s3Service.viewVideoFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+fileName+"\"")
                .body(file);
    }
}