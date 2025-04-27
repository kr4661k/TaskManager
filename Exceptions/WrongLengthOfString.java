package com.tkachenkopetr.spring.Exceptions;

public class WrongLengthOfString extends RuntimeException {
    public WrongLengthOfString(String message) {
        super(message);
    }
}
