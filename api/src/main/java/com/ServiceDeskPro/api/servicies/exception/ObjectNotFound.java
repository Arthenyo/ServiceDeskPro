package com.ServiceDeskPro.api.servicies.exception;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String msg){
        super(msg);
    }
}
