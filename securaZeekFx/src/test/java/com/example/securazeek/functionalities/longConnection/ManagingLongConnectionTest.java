package com.example.securazeek.functionalities.longConnection;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ManagingLongConnectionTest {

    private ManagingLongConnection managingLongConnection;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/LongestConnection.txt";

    public ManagingLongConnectionTest() throws FileNotFoundException, ReadFileException {
        managingLongConnection = new ManagingLongConnection();
        managingLongConnection.getLoadLongestConnection().setFilePath(path);
        managingLongConnection.getLoadLongestConnection().loadFile();
    }

    @Test
    void openFile() throws FileNotFoundException, ReadFileException {
        managingLongConnection.getLoadLongestConnection().setFilePath("ciao/Giulia");
        assertEquals("ciao/Giulia", managingLongConnection.getLoadLongestConnection().getFilePath());
        managingLongConnection.getLoadLongestConnection().setFilePath(path);
        assertTrue(managingLongConnection.getLoadLongestConnection().loadFile());
    }

    @Test
    void displayAllConnections() throws TooManyConnections {
        managingLongConnection.displayAllConnections(managingLongConnection.getLoadLongestConnection().getObjLongestConnection().size());
        assertTrue(managingLongConnection.getObsListDisplay().size() > 0);
    }

    @Test
    void displayConnectionsByIp() {
        managingLongConnection.displayConnectionsByIp("192.168.99.51", "");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIp("", "167.71.97.235");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIp("192.168.99.51", "167.71.97.235");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIp("", "");
        assertEquals(0, managingLongConnection.getObsListIp().size());
    }

    @Test
    void displayConnectionsByIpReverse() {
    }

    @Test
    void displayConnectionsByDuration() {
    }

    @Test
    void connectionsAverage() {
    }

    @Test
    void displayConnectionsByPort() {
    }

    @Test
    void displayConnectionsByProtocol() {
    }

    @Test
    void displayConnectionsByService() {
    }

    @Test
    void countPortOccurrences() {
    }

    @Test
    void countProtocolOccurrences() {
    }

    @Test
    void countServiceOccurrences() {
    }

    @Test
    void getLoadLongestConnection() {
    }

    @Test
    void getObsListDisplay() {
    }

    @Test
    void getObsListIp() {
    }

    @Test
    void getObsListDuration() {
    }

    @Test
    void getObsListPort() {
    }

    @Test
    void getObsListProtocol() {
    }

    @Test
    void getObsListService() {
    }

    @Test
    void getCountPort() {
    }

    @Test
    void getCountProtocol() {
    }

    @Test
    void getCountService() {
    }
}