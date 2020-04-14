package com.app.demo.exceptions;

public class EntityNotFoundException extends FootballManagerException {
    public EntityNotFoundException(String exceptionMessage, Throwable err) {
        super(exceptionMessage, err);
    }

    public EntityNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
