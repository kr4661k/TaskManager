package com.tkachenkopetr.spring.Exceptions;

public class NotUniqueEmail extends RuntimeException {
    public NotUniqueEmail(String message) {
        super(message);
    }
}
