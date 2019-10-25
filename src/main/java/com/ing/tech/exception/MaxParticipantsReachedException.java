package com.ing.tech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MaxParticipantsReachedException extends RuntimeException {

    public MaxParticipantsReachedException() {
    }

    public MaxParticipantsReachedException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
