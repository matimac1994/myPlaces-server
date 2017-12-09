package com.maciejak.myplaces_server.exception.place;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Place ids couldn't be empty")
public class EmptyPlaceIdsException extends RuntimeException {
    public EmptyPlaceIdsException() {
    }

    public EmptyPlaceIdsException(String message) {
        super(message);
    }

    public EmptyPlaceIdsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyPlaceIdsException(Throwable cause) {
        super(cause);
    }

    public EmptyPlaceIdsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
