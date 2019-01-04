package com.sicimike.mayserver.exception;

/**
 * @author sicimike
 * @create 2018-12-14 10:55
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
