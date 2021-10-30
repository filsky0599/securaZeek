package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.loadingFiles.LoadAbsLongestConnection;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagingAbsLongestConnectionTest {

    private ManagingAbsLongestConnection managingAbsLongestConnection;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/AbsLongestConnection.txt";

    public ManagingAbsLongestConnectionTest() throws FileNotFoundException, ReadFileException {
        managingAbsLongestConnection = new ManagingAbsLongestConnection();
        managingAbsLongestConnection.getLoadAbsLongestConnection().setFilePath(path);
        managingAbsLongestConnection.getLoadAbsLongestConnection().loadFile();
    }

    @Test
    void openFile() throws FileNotFoundException, ReadFileException {
        managingAbsLongestConnection.getLoadAbsLongestConnection().setFilePath("ciao/prova");
        assertEquals("ciao/prova", managingAbsLongestConnection.getLoadAbsLongestConnection().getFilePath());

        managingAbsLongestConnection.getLoadAbsLongestConnection().setFilePath(path);
        assertTrue(managingAbsLongestConnection.getLoadAbsLongestConnection().loadFile());
    }

    @Test
    void displayAllConnections() {
        managingAbsLongestConnection.displayAllConnections(managingAbsLongestConnection.getLoadAbsLongestConnection().getObjAbsLongestConnections().size());
        assertTrue(managingAbsLongestConnection.getObsListDisplay().size() > 0);
    }

    @Test
    void displayConnectionsByIp() {
        managingAbsLongestConnection.displayConnectionsByIp("192.168.99.51","");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);

        managingAbsLongestConnection.getObsListByIp().clear();
        managingAbsLongestConnection.displayConnectionsByIp("", "167.71.97.235");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);

        managingAbsLongestConnection.getObsListByIp().clear();
        managingAbsLongestConnection.displayConnectionsByIp("192.168.99.51", "167.71.97.235");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);

        managingAbsLongestConnection.getObsListByIp().clear();
        managingAbsLongestConnection.displayConnectionsByIp("", "");
        assertEquals(0, managingAbsLongestConnection.getObsListByIp().size());
    }

    @Test
    void displayConnectionsByIpReverse() {
        managingAbsLongestConnection.displayConnectionsByIpReverse("192.168.99.51","");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);

        managingAbsLongestConnection.getObsListByIp().clear();
        managingAbsLongestConnection.displayConnectionsByIpReverse("", "167.71.97.235");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);

        managingAbsLongestConnection.getObsListByIp().clear();
        managingAbsLongestConnection.displayConnectionsByIpReverse("192.168.99.51", "167.71.97.235");
        assertTrue(managingAbsLongestConnection.getObsListByIp().size() > 0);
    }

    @Test
    void displayConnectionsByDuration() throws NotValidInsertion {
        managingAbsLongestConnection.displayConnectionsByDuration(20, false);
        assertTrue(managingAbsLongestConnection.getObsListDuration().size() > 0);

        managingAbsLongestConnection.getObsListDuration().clear();
        managingAbsLongestConnection.displayConnectionsByDuration(20, true);
        assertTrue(managingAbsLongestConnection.getObsListDuration().size() > 0);
    }

    @Test
    void connectionsAverage() {
        assertTrue(managingAbsLongestConnection.connectionsAverage() > 0);
    }

    @Test
    void countConnectionsOccurrences() {
        managingAbsLongestConnection.countConnectionsOccurrences();
        assertTrue(managingAbsLongestConnection.getCountConnections().size() > 0);
    }

    @Test
    void getLoadAbsLongestConnection() {
        assertEquals(LoadAbsLongestConnection.class, managingAbsLongestConnection.getLoadAbsLongestConnection().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAbsLongestConnection.getObsListDisplay().getClass());
    }

    @Test
    void getObsListByIp() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAbsLongestConnection.getObsListByIp().getClass());
    }

    @Test
    void getObsListDuration() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAbsLongestConnection.getObsListDuration().getClass());
    }

    @Test
    void getCountConnections() {
        assertEquals(HashMap.class, managingAbsLongestConnection.getCountConnections().getClass());
    }
}