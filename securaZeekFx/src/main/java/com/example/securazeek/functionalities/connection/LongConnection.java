package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.functionalities.fileInformation.LoadingFile;

public interface LongConnection extends LoadingFile {

    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion;
    double connectionsAverage();
}
