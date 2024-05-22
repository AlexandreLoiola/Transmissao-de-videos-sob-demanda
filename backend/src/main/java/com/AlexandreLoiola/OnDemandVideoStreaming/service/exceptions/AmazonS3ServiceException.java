package com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions;

import com.amazonaws.services.s3.model.AmazonS3Exception;

public class AmazonS3ServiceException extends RuntimeException {
    public AmazonS3ServiceException(AmazonS3Exception err) {
        super(createMessage(err), err);
    }

    private static String createMessage(AmazonS3Exception err) {
        if (err.getErrorCode().equals("NoSuchKey")) {
            return "The specified key does not exist.";
        } else {
            return "An error occurred while uploading the file to Amazon S3.";
        }
    }
}
