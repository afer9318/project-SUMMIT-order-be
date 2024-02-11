package com.B2B.SP.order.exceptions.customexceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message){
        super(message);
    }

    public OrderNotFoundException(Throwable cause){
        super(cause);
    }
}
