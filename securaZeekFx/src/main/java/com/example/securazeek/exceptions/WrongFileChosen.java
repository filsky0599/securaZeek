package com.example.securazeek.exceptions;

public class WrongFileChosen extends Exception {

    private final static String message = "Wrong file chosen. Please retry";

    public WrongFileChosen() {
        super(message);
    }
}
