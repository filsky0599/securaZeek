package com.example.securazeek.functionalities.longConnection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;

import java.io.FileNotFoundException;

public interface LongConnection {

    void openFile(String path) throws FileNotFoundException, ReadFileException;
    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion;
    double connectionsAverage();
}
