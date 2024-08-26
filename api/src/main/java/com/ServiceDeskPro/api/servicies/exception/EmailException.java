package com.ServiceDeskPro.api.servicies.exception;

@SuppressWarnings("serial")
public class EmailException extends RuntimeException {

    public EmailException(String msg) {
        super(msg);
    }
}
