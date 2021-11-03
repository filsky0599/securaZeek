package com.example.securazeek.functionalities.dns;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadNumberOfHosts;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagingNumberOfHostsTest {

    private ManagingNumberOfHosts managingNumberOfHosts;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/NumberOfHosts.txt";

    public ManagingNumberOfHostsTest() throws IOException {
        managingNumberOfHosts = new ManagingNumberOfHosts();
        managingNumberOfHosts.getLoadNumberOfHosts().setFilePath(path);
        managingNumberOfHosts.getLoadNumberOfHosts().loadFile();
    }

    @Test
    void openFile() throws IOException {
        managingNumberOfHosts.getLoadNumberOfHosts().setFilePath("ciao/prova");
        assertEquals("ciao/prova", managingNumberOfHosts.getLoadNumberOfHosts().getFilePath());

        managingNumberOfHosts.getLoadNumberOfHosts().setFilePath(path);
        assertTrue(managingNumberOfHosts.getLoadNumberOfHosts().loadFile());
    }

    @Test
    void displayAllHosts() throws TooManyConnections {
        managingNumberOfHosts.displayAllHosts(managingNumberOfHosts.getLoadNumberOfHosts().getObjNumberOfHosts().size());
        assertTrue(managingNumberOfHosts.getObsListDisplay().size() > 0);
    }

    @Test
    void displayByHostNumber() throws NotValidInsertion {
        managingNumberOfHosts.displayByHostNumber(4, false);
        assertTrue(managingNumberOfHosts.getObsListNumberHosts().size() > 0);

        managingNumberOfHosts.getObsListNumberHosts().clear();
        managingNumberOfHosts.displayByHostNumber(4, true);
        assertTrue(managingNumberOfHosts.getObsListNumberHosts().size() > 0);
    }

    @Test
    void displayByHosts() {
        managingNumberOfHosts.displayByHosts("microsoft.com", false);
        assertTrue(managingNumberOfHosts.getObsListHosts().size() > 0);

        managingNumberOfHosts.getObsListHosts().clear();
        managingNumberOfHosts.displayByHosts("microsoft.com", true);
        assertTrue(managingNumberOfHosts.getObsListHosts().size() > 0);
    }

    @Test
    void countMapNumberHosts() {
        managingNumberOfHosts.countMapNumberHosts();
        assertTrue(managingNumberOfHosts.getMapNumberHosts().size() > 0);
    }

    @Test
    void getLoadNumberOfHosts() {
        assertEquals(LoadNumberOfHosts.class, managingNumberOfHosts.getLoadNumberOfHosts().getClass());
    }

    @Test
    void getObsListDisplay() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfHosts.getObsListDisplay().getClass());
    }

    @Test
    void getObsListNumberHosts() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfHosts.getObsListNumberHosts().getClass());
    }

    @Test
    void getObsListHosts() {
        assertEquals(FXCollections.observableArrayList().getClass(), managingNumberOfHosts.getObsListHosts().getClass());
    }

    @Test
    void getMapNumberHosts() {
        assertEquals(HashMap.class, managingNumberOfHosts.getMapNumberHosts().getClass());
    }
}