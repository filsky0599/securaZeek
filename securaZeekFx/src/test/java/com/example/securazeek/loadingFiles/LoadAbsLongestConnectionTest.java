package com.example.securazeek.loadingFiles;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.objConnection.ObjAbsLongestConnection;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LoadAbsLongestConnectionTest {

    private LoadAbsLongestConnection loadAbsLongestConnection;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/AbsLongestConnection.txt";

    public LoadAbsLongestConnectionTest() {
        loadAbsLongestConnection = new LoadAbsLongestConnection();
        loadAbsLongestConnection.setFilePath(path);
        loadAbsLongestConnection.getObjAbsLongestConnections().add(new ObjAbsLongestConnection("192.168.1.43", "343.554.656.232", 4545.545));
        loadAbsLongestConnection.getObjAbsLongestConnections().add(new ObjAbsLongestConnection("127.0.0.1", "124.3.4.2", 2));
        loadAbsLongestConnection.getObjAbsLongestConnections().add(new ObjAbsLongestConnection("234.567.213.796", "192.121.322.4", 15.34));
        loadAbsLongestConnection.getObjAbsLongestConnections().add(new ObjAbsLongestConnection("13.43.2.0", "192.168.2.2", 0));
    }

    @Test
    void loadFile() throws FileNotFoundException, ReadFileException {
        assertTrue(loadAbsLongestConnection.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadAbsLongestConnection.getFilePath());
    }

    @Test
    void setFilePath() {
        loadAbsLongestConnection.setFilePath("pc/local");
        assertEquals("pc/local", loadAbsLongestConnection.getFilePath());
    }

    @Test
    void getObjAbsLongestConnections() {
        assertEquals(4, loadAbsLongestConnection.getObjAbsLongestConnections().size());
    }
}