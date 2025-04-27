package com.tkachenkopetr.spring.Exceptions;

public class NotValidAuthor extends RuntimeException {
    public NotValidAuthor(String message) {
        super(message);
    }
}
