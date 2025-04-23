package com.medspace.infrastructure.rest;

public class GenericResponse {
    public boolean success;
    public String message;
    public Object data;

    public GenericResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
