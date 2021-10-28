package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.functionalities.fileInformation.LoadingFile;

public interface Beacons extends LoadingFile {

    void displayAllConnections(int connectionsDisplayed) throws TooManyConnections;
    void displayConnectionsByIp(String varSourceIp, String varDestIp) ;
    void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) ;
    void displayConnectionsByNumber(int varNumberOfConnections, boolean filterStatus) throws NotValidInsertion;
}
