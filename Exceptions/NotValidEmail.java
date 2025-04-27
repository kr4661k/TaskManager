package com.tkachenkopetr.spring.Exceptions;

public class NotValidEmail extends RuntimeException {
    public NotValidEmail(String message) {super(message);}
}
