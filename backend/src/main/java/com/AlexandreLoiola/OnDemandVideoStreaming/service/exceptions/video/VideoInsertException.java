package com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class VideoInsertException extends DataIntegrityViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public VideoInsertException(String msg) {
        super(msg);
    }

    public VideoInsertException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
