package com.geolisa.exception;

public class ErrorResponseInfo {

    private String message;

    public ErrorResponseInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
