package com.elearn.app.elearnapi.errors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HTTPServerError.class)
    public ResponseEntity<HTTPServerError> handleTopicNotFoundException(HTTPServerError ex) {

        // ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(),
        // ex.getMessage());

        return new ResponseEntity<HTTPServerError>(ex, HttpStatusCode.valueOf(ex.getStatusCode()));
    }
}