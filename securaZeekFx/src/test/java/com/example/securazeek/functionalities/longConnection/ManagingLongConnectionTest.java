package com.example.securazeek.functionalities.longConnection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadLongestConnection;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

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
        managingLongConnection.displayConnectionsByIpReverse("192.168.99.51", "");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIpReverse("", "167.71.97.235");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIpReverse("192.168.99.51", "167.71.97.235");
        assertTrue(managingLongConnection.getObsListIp().size() > 0);

        managingLongConnection.getObsListIp().clear();
        managingLongConnection.displayConnectionsByIpReverse("", "");
        assertEquals(managingLongConnection.getObsListIp().size(), managingLongConnection.getObsListIp().size());
    }

    @Test
    void displayConnectionsByDuration() throws NotValidInsertion {
        managingLongConnection.displayConnectionsByDuration(20.0, false);
        assertTrue(managingLongConnection.getObsListDuration().size() > 0);

        managingLongConnection.getObsListDuration().clear();
        managingLongConnection.displayConnectionsByDuration(20.0, true);
        assertTrue(managingLongConnection.getObsListDuration().size() > 0);
    }

    @Test
    void connectionsAverage() {
        assertTrue(managingLongConnection.connectionsAverage() > 0);
    }

    @Test
    void displayConnectionsByPort() throws NotValidInsertion {
        managingLongConnection.displayConnectionsByPort(80, false);
        assertTrue(managingLongConnection.getObsListPort().size() > 0);

        managingLongConnection.displayConnectionsByPort(80, true);
        assertTrue(managingLongConnection.getObsListPort().size() > 0);
    }

    @Test
    void displayConnectionsByProtocol() {
        managingLongConnection.displayConnectionsByProtocol("udp", false);
        assertTrue(managingLongConnection.getObsListProtocol().size() > 0);

        managingLongConnection.displayConnectionsByProtocol("udp", true);
        assertTrue(managingLongConnection.getObsListProtocol().size() > 0);
    }

    @Test
    void displayConnectionsByService() {
        managingLongConnection.displayConnectionsByService("ssl", false);
        assertTrue(managingLongConnection.getObsListService().size() > 0);

        managingLongConnection.displayConnectionsByService("ssl", true);
        assertTrue(managingLongConnection.getObsListService().size() > 0);
    }

    @Test
    void countPortOccurrences() {
        managingLongConnection.countPortOccurrences();
        assertTrue(managingLongConnection.getCountPort().size() > 0);
    }

    @Test
    void countProtocolOccurrences() {
        managingLongConnection.countProtocolOccurrences();
        assertTrue(managingLongConnection.getCountProtocol().size() > 0);
    }

    @Test
    void countServiceOccurrences() {
        managingLongConnection.countServiceOccurrences();
        assertTrue(managingLongConnection.getCountService().size() > 0);
    }

    @Test
    void getLoadLongestConnection() {
        assertEquals(LoadLongestConnection.class, managingLongConnection.getLoadLongestConnection().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListDisplay().getClass());
    }

    @Test
    void getObsListIp() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListIp().getClass());
    }

    @Test
    void getObsListDuration() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListDuration().getClass());
    }

    @Test
    void getObsListPort() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListPort().getClass());
    }

    @Test
    void getObsListProtocol() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListProtocol().getClass());
    }

    @Test
    void getObsListService() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongConnection.getObsListService().getClass());
    }

    @Test
    void getCountPort() {
        assertEquals(HashMap.class, managingLongConnection.getCountPort().getClass());
    }

    @Test
    void getCountProtocol() {
        assertEquals(HashMap.class, managingLongConnection.getCountProtocol().getClass());
    }

    @Test
    void getCountService() {
        assertEquals(HashMap.class, managingLongConnection.getCountService().getClass());
    }
}