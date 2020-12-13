package de.eurofunk.broker.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Basic error handler, which handles specific exceptions types
 * and returns their message as plain string.
 */
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
