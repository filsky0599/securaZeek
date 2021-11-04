package com.example.securazeek.loadingFiles;

import com.example.securazeek.objModel.ObjAmountOfData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoadAmountOfDataTest {

    private LoadAmountOfData loadAmountOfData;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/AmountOfData.txt";

    public LoadAmountOfDataTest() {
        loadAmountOfData = new LoadAmountOfData();
        loadAmountOfData.setFilePath(path);
        loadAmountOfData.getObjAmountOfData().add(new ObjAmountOfData(33, "124.424.312.123", "124.4.5.2", 44));
        loadAmountOfData.getObjAmountOfData().add(new ObjAmountOfData(3021, "132.232.32.3", "32.4.2.65", 0));
        loadAmountOfData.getObjAmountOfData().add(new ObjAmountOfData(1, "127.0.0.1", "213.323.465.754", 3023230232.34));
    }

    @Test
    void loadFile() throws IOException {
        assertTrue(loadAmountOfData.loadFile());
    }

    @Test
    void getFilePath() {
        assertEquals(path, loadAmountOfData.getFilePath());
    }

    @Test
    void setFilePath() {
        loadAmountOfData.setFilePath("ciao/prova/secura");
        assertEquals("ciao/prova/secura", loadAmountOfData.getFilePath());
    }

    @Test
    void getObjAmountOfData() {
        assertEquals(3, loadAmountOfData.getObjAmountOfData().size());
    }
}