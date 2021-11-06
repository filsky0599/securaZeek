package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.functionalities.LoadFile;

public interface LongConnection extends LoadFile {

    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion;
    double connectionsAverage();
}
