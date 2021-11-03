package com.example.securazeek.loadingFiles;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadFileInformationTest {

    private LoadFileInformation loadFileInformation;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/FileInformation.txt";

    public LoadFileInformationTest() {
        loadFileInformation = new LoadFileInformation();
        loadFileInformation.setFilePath(path);
    }

    @Test
    void loadFile() throws IOException {
        assertTrue(loadFileInformation.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadFileInformation.getFilePath());
    }

    @Test
    void setFilePath() {
        loadFileInformation.setFilePath("prova/ciao/test2");
        assertEquals("prova/ciao/test2", loadFileInformation.getFilePath());
    }

    @Test
    void getFileText() {
        assertNull(loadFileInformation.getFileText());
    }
}