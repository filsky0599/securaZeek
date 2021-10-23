package com.example.securazeek.exceptions;

public class TooManyConnections extends Exception{

    private static final String message = "The are not as many connections as requested. Insert a minor number";

    public TooManyConnections() {
        super(message);
    }
}
