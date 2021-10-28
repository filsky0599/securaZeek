package com.example.securazeek.exceptions;

public class ReadFileException extends Exception {

    private static final String message = "File not readable. Please retry.";

    public ReadFileException() {
        super(message);
    }
}
