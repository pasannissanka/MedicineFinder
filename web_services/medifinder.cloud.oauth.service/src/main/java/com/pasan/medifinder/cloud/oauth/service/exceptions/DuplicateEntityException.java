package com.pasan.medifinder.cloud.oauth.service.exceptions;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
