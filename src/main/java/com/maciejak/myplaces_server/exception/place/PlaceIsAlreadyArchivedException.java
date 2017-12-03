package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Place is already archived")
public class PlaceIsAlreadyArchivedException extends RuntimeException {
    public PlaceIsAlreadyArchivedException() {
    }

    public PlaceIsAlreadyArchivedException(String message) {
        super(message);
    }

    public PlaceIsAlreadyArchivedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceIsAlreadyArchivedException(Throwable cause) {
        super(cause);
    }

    public PlaceIsAlreadyArchivedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
