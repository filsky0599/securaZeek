package com.example.securazeek.loadingFiles;

import com.example.securazeek.objConnection.ObjNumberOfConnections;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadNumberOfConnectionsTest {

    private LoadNumberOfConnections loadNumberOfConnections;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/NumberOfConnections.txt";

    public LoadNumberOfConnectionsTest() {
        loadNumberOfConnections = new LoadNumberOfConnections();
        loadNumberOfConnections.setFilePath(path);
        loadNumberOfConnections.getObjNumberOfConnections().add(new ObjNumberOfConnections(332, "192.232.564.767", "124.0.0.1"));
        loadNumberOfConnections.getObjNumberOfConnections().add(new ObjNumberOfConnections(1, "192.676.543.743", "464.534.567.343"));
        loadNumberOfConnections.getObjNumberOfConnections().add(new ObjNumberOfConnections(35, "192.434.235.878", "127.232.232.1"));
    }

    @Test
    void loadFile() throws IOException {
        assertTrue(loadNumberOfConnections.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadNumberOfConnections.getFilePath());
    }

    @Test
    void setFilePath() {
        loadNumberOfConnections.setFilePath("ciao/test");
        assertEquals("ciao/test", loadNumberOfConnections.getFilePath());
    }

    @Test
    void getObjNumberOfConnections() {
        assertEquals(3, loadNumberOfConnections.getObjNumberOfConnections().size());
    }
}