package com.elearn.app.elearnapi.errors;

import org.springframework.http.HttpStatus;

public class HTTPServerError extends RuntimeException {
    private int statusCode;

    public HTTPServerError(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode.value();
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
