package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadNumberOfConnections;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagingNumberOfConnectionsTest {

    private ManagingNumberOfConnections managingNumberOfConnections;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/NumberOfConnections.txt";

    public ManagingNumberOfConnectionsTest() throws FileNotFoundException, ReadFileException {
        managingNumberOfConnections = new ManagingNumberOfConnections();
        managingNumberOfConnections.getLoadNumberOfConnections().setFilePath(path);
        managingNumberOfConnections.getLoadNumberOfConnections().loadFile();
    }

    @Test
    void openFile() throws FileNotFoundException, ReadFileException {
        managingNumberOfConnections.getLoadNumberOfConnections().setFilePath("ciao/prova");
        assertEquals("ciao/prova", managingNumberOfConnections.getLoadNumberOfConnections().getFilePath());

        managingNumberOfConnections.getLoadNumberOfConnections().setFilePath(path);
        assertTrue(managingNumberOfConnections.getLoadNumberOfConnections().loadFile());
    }

    @Test
    void displayAllConnections() throws TooManyConnections {
        managingNumberOfConnections.displayAllConnections(managingNumberOfConnections.getLoadNumberOfConnections().getObjNumberOfConnections().size());
        assertTrue(managingNumberOfConnections.getObsListDisplay().size() > 0);
    }

    @Test
    void displayConnectionsByIp() {
        managingNumberOfConnections.displayConnectionsByIp("192.168.99.51", "");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);

        managingNumberOfConnections.getObsListByIP().clear();
        managingNumberOfConnections.displayConnectionsByIp("", "208.67.222.222");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);

        managingNumberOfConnections.getObsListByIP().clear();
        managingNumberOfConnections.displayConnectionsByIp("192.168.99.55", "224.0.0.251");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);

        managingNumberOfConnections.getObsListByIP().clear();
        managingNumberOfConnections.displayConnectionsByIp("", "");
        assertEquals(0, managingNumberOfConnections.getObsListByIP().size());
    }

    @Test
    void displayConnectionsByIpReverse() {
        managingNumberOfConnections.displayConnectionsByIpReverse("192.168.99.51", "");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);

        managingNumberOfConnections.getObsListByIP().clear();
        managingNumberOfConnections.displayConnectionsByIpReverse("", "208.67.222.222");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);

        managingNumberOfConnections.getObsListByIP().clear();
        managingNumberOfConnections.displayConnectionsByIpReverse("192.168.99.55", "224.0.0.251");
        assertTrue(managingNumberOfConnections.getObsListByIP().size() > 0);
    }

    @Test
    void displayConnectionsByNumber() throws NotValidInsertion {
        managingNumberOfConnections.displayConnectionsByNumber(100, false);
        assertTrue(managingNumberOfConnections.getObsListByNumberConnections().size() > 0);

        managingNumberOfConnections.getObsListByNumberConnections().clear();
        managingNumberOfConnections.displayConnectionsByNumber(100, true);
        assertTrue(managingNumberOfConnections.getObsListByNumberConnections().size() > 0);
    }

    @Test
    void countIp() {
        managingNumberOfConnections.countIp();
        assertTrue(managingNumberOfConnections.getCountIpMap().size() > 0);
    }

    @Test
    void getLoadNumberOfConnections() {
        assertEquals(LoadNumberOfConnections.class, managingNumberOfConnections.getLoadNumberOfConnections().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfConnections.getObsListDisplay().getClass());
    }

    @Test
    void getObsListByIP() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfConnections.getObsListByIP().getClass());
    }

    @Test
    void getObsListByNumberConnections() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfConnections.getObsListByNumberConnections().getClass());
    }

    @Test
    void getCountIpMap() {
        assertEquals(HashMap.class, managingNumberOfConnections.getCountIpMap().getClass());
    }
}