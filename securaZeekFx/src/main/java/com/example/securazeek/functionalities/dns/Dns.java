package com.example.securazeek.functionalities.dns;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.functionalities.fileInformation.LoadingFile;

public interface Dns extends LoadingFile {

    void displayAllHosts(int hostsDisplayed);
    void displayByHostNumber(int varNumberHosts, boolean filterStatus) throws NotValidInsertion;
    void displayByHosts(String varHosts, boolean filterStatus);
}
