package com.example.securazeek.functionalities.longConnection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.functionalities.Functionalities;

public interface LongConnection extends Functionalities {

    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion;
    double connectionsAverage();
}
