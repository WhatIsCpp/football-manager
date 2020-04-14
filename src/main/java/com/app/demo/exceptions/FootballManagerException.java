package com.app.demo.exceptions;

public class FootballManagerException extends RuntimeException {
    public FootballManagerException(String exceptionMessage, Throwable err) {
        super(exceptionMessage, err);
    }

    public FootballManagerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
