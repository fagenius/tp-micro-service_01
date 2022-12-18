package com.billingueservice.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String messaage){
        super(messaage);
    }
}
