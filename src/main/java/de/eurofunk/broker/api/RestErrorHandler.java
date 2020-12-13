package de.eurofunk.broker.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            IllegalStateException.class,
            RuntimeException.class
    })
    public String handleExceptionsToBadRequestResponse(Exception exception) {
        return exception.getMessage();
    }

}
