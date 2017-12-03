package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Place coordinates is wrong")
public class WrongPlaceCoordinatesException extends RuntimeException {
    public WrongPlaceCoordinatesException() {
    }

    public WrongPlaceCoordinatesException(String message) {
        super(message);
    }

    public WrongPlaceCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPlaceCoordinatesException(Throwable cause) {
        super(cause);
    }

    public WrongPlaceCoordinatesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
