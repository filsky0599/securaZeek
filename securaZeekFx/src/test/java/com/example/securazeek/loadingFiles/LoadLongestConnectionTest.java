package com.example.securazeek.loadingFiles;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.objConnection.ObjLongestConnection;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LoadLongestConnectionTest {

    private LoadLongestConnection loadLongestConnection;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/LongestConnection.txt";

    public LoadLongestConnectionTest() {
        loadLongestConnection = new LoadLongestConnection();
        loadLongestConnection.setFilePath(path);
        loadLongestConnection.getObjLongestConnection().add(new ObjLongestConnection("192.168.1.43", "343.554.656.232", 4545.545, 8080, "udp", "ssl"));
        loadLongestConnection.getObjLongestConnection().add(new ObjLongestConnection("127.0.0.1", "124.3.4.2", 2, 80, "tcp", "dns"));
        loadLongestConnection.getObjLongestConnection().add(new ObjLongestConnection("234.567.213.796", "192.121.322.4", 15.34, 124, "udp", "Not Defined"));
    }

    @Test
    void loadFile() throws FileNotFoundException, ReadFileException {
        assertTrue(loadLongestConnection.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadLongestConnection.getFilePath());
    }

    @Test
    void setFilePath() {
        loadLongestConnection.setFilePath("prova/test/ciao");
        assertEquals("prova/test/ciao", loadLongestConnection.getFilePath());
    }

    @Test
    void getObjLongestConnection() {
    }
}