package com.AlexandreLoiola.OnDemandVideoStreaming.service;

import com.AlexandreLoiola.OnDemandVideoStreaming.rest.dto.FolderDto;
import com.AlexandreLoiola.OnDemandVideoStreaming.rest.form.CreateFolderForm;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.AmazonS3ServiceException;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.FileUploadException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class S3Service {

    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public S3Object getFile(String keyName) {
        try {
            return s3client.getObject(bucketName, keyName);
        } catch (AmazonS3Exception err) {
            log.error("Error retrieving file " + keyName + " from S3. Error code: " + err.getErrorCode());
            throw new AmazonS3ServiceException(err);
        }
    }

    public List<S3ObjectSummary> listobjects(String folderName) {
        ObjectListing objectListing = s3client.listObjects(bucketName, folderName);
        return objectListing.getObjectSummaries();
    }

    public void createFolder(CreateFolderForm createFolderForm) {
        String folderName =  createFolderForm.getFolderName() + "/";
        s3client.putObject(bucketName, folderName, "");
    }

    public List<FolderDto> listFolders() {
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withDelimiter("/");
        ListObjectsV2Result listing = s3client.listObjectsV2(req);
        List<FolderDto> folders = new ArrayList<>();
        for (String commonPrefix  : listing.getCommonPrefixes()) {
            FolderDto dto = new FolderDto();
            folders.add(dto);
        }
        return folders;
    }

    public void uploadFile(String id, String keyName, String folderName, MultipartFile file) throws IOException {
        log.info("Starting file upload: " + folderName + "/" + keyName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("id", id);
        try {
            var outObjectResult = s3client.putObject(bucketName, folderName + "/" + keyName, file.getInputStream(), metadata);
            log.info("File upload successful for " + keyName + ". Metadata: " + outObjectResult.getMetadata());
        } catch (AmazonS3Exception err) {
            log.error("Error uploading file " + keyName + " to S3. Error code: " + err.getErrorCode());
            throw new AmazonS3ServiceException(err);
        } catch (IOException err) {
            log.error("IO error while uploading file " + keyName, err);
            throw new FileUploadException("Error uploading file" + keyName);
        }
    }

    public URL generateTempUrl(String keyName, int expirationTimeInMinutes) {
        try {
            Date expiration = new Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * expirationTimeInMinutes;
            expiration.setTime(expTimeMillis);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, keyName)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);

            return url;
        } catch (AmazonS3Exception e) {
            log.error("Error generating temporary URL for file " + keyName + " from S3. Error code: " + e.getErrorCode());
            throw new AmazonS3ServiceException(e);
        }
    }


    public String getFileUrl(String keyName) {
        try {
            URL url = s3client.getUrl(bucketName, keyName);
            return url.toString();
        } catch (AmazonS3Exception err) {
            log.error("Error retrieving URL for file " + keyName + " from S3. Error code: " + err.getErrorCode());
            throw new AmazonS3ServiceException(err);
        }
    }


    public InputStreamResource downloadFile(String fileName) {
        return new InputStreamResource(getFile(fileName).getObjectContent());
    }

    public InputStreamResource viewImageFile(String fileNmame) {
        return new InputStreamResource(getFile(fileNmame).getObjectContent());
    }

    public InputStreamResource viewVideoFile(String fileNmame) {
        return new InputStreamResource(getFile(fileNmame).getObjectContent());
    }
}