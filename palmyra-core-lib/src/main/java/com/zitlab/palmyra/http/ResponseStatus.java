package com.zitlab.palmyra.http;

public class ResponseStatus {

    private boolean status;
    private int code;
    private String message;

    public ResponseStatus(boolean status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
