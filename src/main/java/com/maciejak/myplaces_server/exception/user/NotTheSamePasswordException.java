package com.maciejak.myplaces_server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Passwords are not the same")
public class NotTheSamePasswordException extends RuntimeException {
    public NotTheSamePasswordException() {
    }

    public NotTheSamePasswordException(String message) {
        super(message);
    }

    public NotTheSamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotTheSamePasswordException(Throwable cause) {
        super(cause);
    }

    public NotTheSamePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
