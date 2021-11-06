package com.example.securazeek.functionalities.dns;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.functionalities.LoadFile;

public interface Dns extends LoadFile {

    void displayAllHosts(int hostsDisplayed) throws TooManyConnections;
    void displayByHostNumber(int varNumberHosts, boolean filterStatus) throws NotValidInsertion;
    void displayByHosts(String varHosts, boolean filterStatus);
}
