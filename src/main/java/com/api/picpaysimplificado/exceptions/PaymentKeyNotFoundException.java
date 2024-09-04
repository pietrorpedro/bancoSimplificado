package com.api.picpaysimplificado.exceptions;

public class PaymentKeyNotFoundException extends RuntimeException{
    
    public PaymentKeyNotFoundException(String message) {
        super(message);
    }
}
