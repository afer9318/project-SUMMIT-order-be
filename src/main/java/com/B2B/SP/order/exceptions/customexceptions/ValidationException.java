package com.B2B.SP.order.exceptions.customexceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message){
        super(message);
    }

    public ValidationException(Throwable cause){
        super(cause);
    }
}
