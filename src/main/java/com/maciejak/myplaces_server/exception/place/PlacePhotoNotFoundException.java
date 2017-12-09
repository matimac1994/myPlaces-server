package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Photo not found")
public class PlacePhotoNotFoundException extends RuntimeException {
    public PlacePhotoNotFoundException() {
    }

    public PlacePhotoNotFoundException(String message) {
        super(message);
    }

    public PlacePhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlacePhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    public PlacePhotoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
