package com.lab.coffeeshop.exception;

import lombok.Getter;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
