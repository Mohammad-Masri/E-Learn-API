package com.elearn.app.elearnapi.errors;

import org.springframework.http.HttpStatus;

public class HTTPServerError extends RuntimeException {
    private String message;
    private int statusCode;

    public HTTPServerError(HttpStatus statusCode, String message) {
        super();
        this.statusCode = statusCode.value();

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
