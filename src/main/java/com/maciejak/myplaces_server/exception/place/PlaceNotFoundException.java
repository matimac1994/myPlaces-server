package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Place not found")
public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() {
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceNotFoundException(Throwable cause) {
        super(cause);
    }

    public PlaceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
