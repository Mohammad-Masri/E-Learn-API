package com.elearn.app.elearnapi.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elearn.app.elearnapi.utilities.PrintUtilities;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HTTPServerError.class)
    public ResponseEntity<ErrorResponse> handleHTTPServerError(HTTPServerError ex) {
        PrintUtilities.println("HTTPServerError caught: " + ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }
}