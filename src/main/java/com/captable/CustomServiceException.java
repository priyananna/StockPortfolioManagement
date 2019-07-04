package com.captable;


public class CustomServiceException extends Exception{
    public CustomServiceException(){
        super();
    }
    public CustomServiceException(final String message, final Throwable cause){
        super(message, cause);
    }
    public CustomServiceException(final String message){
        super(message);
    }
}
