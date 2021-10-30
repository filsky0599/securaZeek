package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadLongestConnection;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagingLongestConnectionTest {

    private ManagingLongestConnection managingLongestConnection;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/LongestConnection.txt";

    public ManagingLongestConnectionTest() throws FileNotFoundException, ReadFileException {
        managingLongestConnection = new ManagingLongestConnection();
        managingLongestConnection.getLoadLongestConnection().setFilePath(path);
        managingLongestConnection.getLoadLongestConnection().loadFile();
    }

    @Test
    void openFile() throws FileNotFoundException, ReadFileException {
        managingLongestConnection.getLoadLongestConnection().setFilePath("ciao/Giulia");
        assertEquals("ciao/Giulia", managingLongestConnection.getLoadLongestConnection().getFilePath());

        managingLongestConnection.getLoadLongestConnection().setFilePath(path);
        assertTrue(managingLongestConnection.getLoadLongestConnection().loadFile());
    }

    @Test
    void displayAllConnections() throws TooManyConnections {
        managingLongestConnection.displayAllConnections(managingLongestConnection.getLoadLongestConnection().getObjLongestConnection().size());
        assertTrue(managingLongestConnection.getObsListDisplay().size() > 0);
    }

    @Test
    void displayConnectionsByIp() {
        managingLongestConnection.displayConnectionsByIp("192.168.99.51", "");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);

        managingLongestConnection.getObsListIp().clear();
        managingLongestConnection.displayConnectionsByIp("", "167.71.97.235");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);

        managingLongestConnection.getObsListIp().clear();
        managingLongestConnection.displayConnectionsByIp("192.168.99.51", "167.71.97.235");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);

        managingLongestConnection.getObsListIp().clear();
        managingLongestConnection.displayConnectionsByIp("", "");
        assertEquals(0, managingLongestConnection.getObsListIp().size());
    }

    @Test
    void displayConnectionsByIpReverse() {
        managingLongestConnection.displayConnectionsByIpReverse("192.168.99.51", "");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);

        managingLongestConnection.getObsListIp().clear();
        managingLongestConnection.displayConnectionsByIpReverse("", "167.71.97.235");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);

        managingLongestConnection.getObsListIp().clear();
        managingLongestConnection.displayConnectionsByIpReverse("192.168.99.51", "167.71.97.235");
        assertTrue(managingLongestConnection.getObsListIp().size() > 0);
    }

    @Test
    void displayConnectionsByDuration() throws NotValidInsertion {
        managingLongestConnection.displayConnectionsByDuration(20.0, false);
        assertTrue(managingLongestConnection.getObsListDuration().size() > 0);

        managingLongestConnection.getObsListDuration().clear();
        managingLongestConnection.displayConnectionsByDuration(20.0, true);
        assertTrue(managingLongestConnection.getObsListDuration().size() > 0);
    }

    @Test
    void connectionsAverage() {
        assertTrue(managingLongestConnection.connectionsAverage() > 0);
    }

    @Test
    void displayConnectionsByPort() throws NotValidInsertion {
        managingLongestConnection.displayConnectionsByPort(80, false);
        assertTrue(managingLongestConnection.getObsListPort().size() > 0);

        managingLongestConnection.displayConnectionsByPort(80, true);
        assertTrue(managingLongestConnection.getObsListPort().size() > 0);
    }

    @Test
    void displayConnectionsByProtocol() {
        managingLongestConnection.displayConnectionsByProtocol("udp", false);
        assertTrue(managingLongestConnection.getObsListProtocol().size() > 0);

        managingLongestConnection.displayConnectionsByProtocol("udp", true);
        assertTrue(managingLongestConnection.getObsListProtocol().size() > 0);
    }

    @Test
    void displayConnectionsByService() {
        managingLongestConnection.displayConnectionsByService("ssl", false);
        assertTrue(managingLongestConnection.getObsListService().size() > 0);

        managingLongestConnection.displayConnectionsByService("ssl", true);
        assertTrue(managingLongestConnection.getObsListService().size() > 0);
    }

    @Test
    void countPortOccurrences() {
        managingLongestConnection.countPortOccurrences();
        assertTrue(managingLongestConnection.getCountPort().size() > 0);
    }

    @Test
    void countProtocolOccurrences() {
        managingLongestConnection.countProtocolOccurrences();
        assertTrue(managingLongestConnection.getCountProtocol().size() > 0);
    }

    @Test
    void countServiceOccurrences() {
        managingLongestConnection.countServiceOccurrences();
        assertTrue(managingLongestConnection.getCountService().size() > 0);
    }

    @Test
    void getLoadLongestConnection() {
        assertEquals(LoadLongestConnection.class, managingLongestConnection.getLoadLongestConnection().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListDisplay().getClass());
    }

    @Test
    void getObsListIp() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListIp().getClass());
    }

    @Test
    void getObsListDuration() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListDuration().getClass());
    }

    @Test
    void getObsListPort() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListPort().getClass());
    }

    @Test
    void getObsListProtocol() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListProtocol().getClass());
    }

    @Test
    void getObsListService() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingLongestConnection.getObsListService().getClass());
    }

    @Test
    void getCountPort() {
        assertEquals(HashMap.class, managingLongestConnection.getCountPort().getClass());
    }

    @Test
    void getCountProtocol() {
        assertEquals(HashMap.class, managingLongestConnection.getCountProtocol().getClass());
    }

    @Test
    void getCountService() {
        assertEquals(HashMap.class, managingLongestConnection.getCountService().getClass());
    }
}