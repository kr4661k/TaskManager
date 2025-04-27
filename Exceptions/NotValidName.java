package com.tkachenkopetr.spring.Exceptions;

public class NotValidName extends RuntimeException {
    public NotValidName(String message) {super(message);}
}
