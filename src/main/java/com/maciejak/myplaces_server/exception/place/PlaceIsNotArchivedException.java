package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Place is not already archived")
public class PlaceIsNotArchivedException extends RuntimeException {
    public PlaceIsNotArchivedException() {
    }

    public PlaceIsNotArchivedException(String message) {
        super(message);
    }

    public PlaceIsNotArchivedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceIsNotArchivedException(Throwable cause) {
        super(cause);
    }

    public PlaceIsNotArchivedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
