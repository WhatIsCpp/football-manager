package com.app.demo.exceptions;

public class FootballPlayerNotLegalException extends FootballManagerException{
    public FootballPlayerNotLegalException(String exceptionMessage, Throwable err){
        super(exceptionMessage, err);
    }
    public FootballPlayerNotLegalException(String exceptionMessage){
        super(exceptionMessage);
    }
}
