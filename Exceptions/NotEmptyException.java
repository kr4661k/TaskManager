package com.tkachenkopetr.spring.Exceptions;

public class NotEmptyException extends RuntimeException {
    public NotEmptyException(String message) {
        super(message);
    }
}
