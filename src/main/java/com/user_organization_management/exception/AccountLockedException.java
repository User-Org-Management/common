package com.user_organization_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountLockedException extends RuntimeException {
    public AccountLockedException(String message) {
        super(message);
    }
}
