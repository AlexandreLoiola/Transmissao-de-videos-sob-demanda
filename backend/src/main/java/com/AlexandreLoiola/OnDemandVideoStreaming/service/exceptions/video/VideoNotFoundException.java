package com.AlexandreLoiola.OnDemandVideoStreaming.service.exceptions.video;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;

public class VideoNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public VideoNotFoundException(String msg) {
        super(msg);
    }

    public VideoNotFoundException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}
