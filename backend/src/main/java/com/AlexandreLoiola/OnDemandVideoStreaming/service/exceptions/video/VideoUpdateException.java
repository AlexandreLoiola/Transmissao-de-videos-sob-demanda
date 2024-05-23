package com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video;

import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

public class VideoUpdateException extends DataIntegrityViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public VideoUpdateException(String msg) {
        super(msg);
    }

    public VideoUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
