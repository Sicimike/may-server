package com.sicimike.mayserver.exception;

/**
 * appKey无效异常
 * @author sicimike
 * @create 2018-12-13 15:37
 */
public class AppKeyInvalidException extends RuntimeException{

    public AppKeyInvalidException(){

    }

    public AppKeyInvalidException(String message){
        super(message);
    }
}
