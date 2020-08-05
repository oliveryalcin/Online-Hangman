package ca.cmpt213.a4.onlinehangman.controllers;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Date;

public class GameException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public GameException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
