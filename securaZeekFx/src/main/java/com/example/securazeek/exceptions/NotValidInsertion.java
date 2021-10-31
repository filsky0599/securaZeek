package com.example.securazeek.exceptions;

public class NotValidInsertion extends Exception {

    private static final String message = "The insertion is not valid. Please retry.";

    public NotValidInsertion() {
        super(message);
    }
}
