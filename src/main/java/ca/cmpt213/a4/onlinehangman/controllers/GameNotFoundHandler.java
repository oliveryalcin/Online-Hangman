package ca.cmpt213.a4.onlinehangman.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@ControllerAdvice
public class GameNotFoundHandler {
    /*
    //handle specific exceptions
    //handle global exceptions
    @ExceptionHandler(value = {GameNotFound.class})
    public ResponseEntity<Object> handleGameNotFoundException
    (GameNotFound e, WebRequest request) {
        // 1. create payload containing exception details
        // Return response entity

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        GameException gameException = new GameException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(gameException, notFound);
    }

     */
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(GameNotFound.class)
    public ModelAndView handleNoHandlerFoundException() {
        return new ModelAndView("gamenotfound");
    }
}
