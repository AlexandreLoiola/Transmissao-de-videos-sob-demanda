package com.AlexandreLoiola.OnDemandVideoStreaming.rest.exceptionHandler;

import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.AmazonS3ServiceException;
import com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.FileUploadException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ExceptionDto> handleFileUploadException(FileUploadException ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage();
        ExceptionDto exceptionsDto = new ExceptionDto(
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "File Upload Error",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionDto exceptionsDto = new ExceptionDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage();
        ExceptionDto exceptionsDto = new ExceptionDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AmazonS3ServiceException.class)
    public ResponseEntity<ExceptionDto> handleAmazonS3ServiceException(AmazonS3ServiceException ex, HttpServletRequest request) {
        String errorMsg = ex.getMessage();
        ExceptionDto exceptionsDto = new ExceptionDto(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Amazon S3 Service Error",
                errorMsg,
                request.getRequestURI());
        return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_REQUEST);
    }
}