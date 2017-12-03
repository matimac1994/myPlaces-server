package com.maciejak.myplaces_server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username already exists, choose other")
public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message){super(message);}

    public UsernameAlreadyExistException() { super();
    }

    public UsernameAlreadyExistException(String message, Throwable cause){
        super(message, cause);
    }

    public UsernameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public UsernameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
