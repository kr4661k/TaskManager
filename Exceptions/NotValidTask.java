package com.tkachenkopetr.spring.Exceptions;

public class NotValidTask extends RuntimeException {
    public NotValidTask(String message) {
        super(message);
    }
}
