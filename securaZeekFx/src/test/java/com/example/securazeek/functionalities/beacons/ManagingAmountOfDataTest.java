package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadAmountOfData;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagingAmountOfDataTest {

    private ManagingAmountOfData managingAmountOfData;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/AmountOfData.txt";

    public ManagingAmountOfDataTest() throws FileNotFoundException, ReadFileException {
        managingAmountOfData = new ManagingAmountOfData();
        managingAmountOfData.getLoadAmountOfData().setFilePath(path);
        managingAmountOfData.getLoadAmountOfData().loadFile();
    }

    @Test
    void openFile() throws FileNotFoundException, ReadFileException {
        managingAmountOfData.getLoadAmountOfData().setFilePath("ciao/prova");
        assertEquals("ciao/prova", managingAmountOfData.getLoadAmountOfData().getFilePath());

        managingAmountOfData.getLoadAmountOfData().setFilePath(path);
        assertTrue(managingAmountOfData.getLoadAmountOfData().loadFile());
    }

    @Test
    void displayAllConnections() throws TooManyConnections {
        managingAmountOfData.displayAllConnections(managingAmountOfData.getLoadAmountOfData().getObjAmountOfData().size());
        assertTrue(managingAmountOfData.getObsListDisplay().size() > 0);
    }

    @Test
    void displayConnectionsByIp() {
        managingAmountOfData.displayConnectionsByIp("192.168.99.51", "");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);

        managingAmountOfData.getObsListIp().clear();
        managingAmountOfData.displayConnectionsByIp("", "224.0.0.252");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);

        managingAmountOfData.getObsListIp().clear();
        managingAmountOfData.displayConnectionsByIp("192.168.99.10", "224.0.0.251");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);

        managingAmountOfData.getObsListIp().clear();
        managingAmountOfData.displayConnectionsByIp("", "");
        assertEquals(0, managingAmountOfData.getObsListIp().size());
    }

    @Test
    void displayConnectionsByIpReverse() {
        managingAmountOfData.displayConnectionsByIpReverse("192.168.99.51", "");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);

        managingAmountOfData.getObsListIp().clear();
        managingAmountOfData.displayConnectionsByIpReverse("", "224.0.0.252");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);

        managingAmountOfData.getObsListIp().clear();
        managingAmountOfData.displayConnectionsByIpReverse("192.168.99.10", "224.0.0.251");
        assertTrue(managingAmountOfData.getObsListIp().size() > 0);
    }

    @Test
    void displayConnectionsByNumber() throws NotValidInsertion {
        managingAmountOfData.displayConnectionsByNumber(100, false);
        assertTrue(managingAmountOfData.getObsListNumber().size() > 0);

        managingAmountOfData.getObsListNumber().clear();
        managingAmountOfData.displayConnectionsByNumber(100, true);
        assertTrue(managingAmountOfData.getObsListNumber().size() > 0);
    }

    @Test
    void displayConnectionsByData() throws NotValidInsertion {
        managingAmountOfData.displayConnectionsByData(200, false);
        assertTrue(managingAmountOfData.getObsListData().size() > 0);

        managingAmountOfData.getObsListData().clear();
        managingAmountOfData.displayConnectionsByData(200, true);
        assertTrue(managingAmountOfData.getObsListData().size() > 0);
    }

    @Test
    void dataMap() {
        managingAmountOfData.dataMap();
        assertTrue(managingAmountOfData.getDataChart().size() > 0);
    }

    @Test
    void numberMap() {
        managingAmountOfData.numberMap();
        assertTrue(managingAmountOfData.getNumberChart().size() > 0);
    }

    @Test
    void getLoadAmountOfData() {
        assertEquals(LoadAmountOfData.class, managingAmountOfData.getLoadAmountOfData().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAmountOfData.getObsListDisplay().getClass());
    }

    @Test
    void getObsListIp() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAmountOfData.getObsListIp().getClass());
    }

    @Test
    void getObsListData() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAmountOfData.getObsListData().getClass());
    }

    @Test
    void getObsListNumber() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingAmountOfData.getObsListNumber().getClass());
    }

    @Test
    void getDataChart() {
        assertEquals(HashMap.class, managingAmountOfData.getDataChart().getClass());
    }
}