package com.example.securazeek.loadingFiles;

import com.example.securazeek.objConnection.ObjNumberOfHosts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadNumberOfHostsTest {

    private LoadNumberOfHosts loadNumberOfHosts;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/NumberOfHosts.txt";

    public LoadNumberOfHostsTest() {
        loadNumberOfHosts = new LoadNumberOfHosts();
        loadNumberOfHosts.setFilePath(path);
        loadNumberOfHosts.getObjNumberOfHosts().add(new ObjNumberOfHosts(33, "microsoft.it"));
        loadNumberOfHosts.getObjNumberOfHosts().add(new ObjNumberOfHosts(13, "amazon.it"));
        loadNumberOfHosts.getObjNumberOfHosts().add(new ObjNumberOfHosts(2, "apple.com"));
    }

    @Test
    void loadFile() throws IOException {
        assertTrue(loadNumberOfHosts.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadNumberOfHosts.getFilePath());
    }

    @Test
    void setFilePath() {
        loadNumberOfHosts.setFilePath("pluto/gian");
        assertEquals("pluto/gian", loadNumberOfHosts.getFilePath());
    }

    @Test
    void getObjNumberOfHosts() {
        assertEquals(3, loadNumberOfHosts.getObjNumberOfHosts().size());
    }
}