package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;

import java.io.FileNotFoundException;

public interface Beacons {

    void openFile(String path) throws FileNotFoundException, ReadFileException;
    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByNumber(int varNumberOfConnections, boolean filterStatus) throws NotValidInsertion;
}
