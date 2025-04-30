package com.user_organization_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadCredentialsAuthException extends RuntimeException{

    public BadCredentialsAuthException(String message){
        super(message);
    }
}
