package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.loadingFiles.LoadFileInformation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ManagingFileInformationTest {

    private ManagingFileInformation managingFileInformation;
    private final String path = "/Users/filipporognoni/Documents/Programmazione/Progetti Git/securaZeek/DatasetEsempio/Results/FileInformation.txt";

    public ManagingFileInformationTest() throws IOException {
        managingFileInformation = new ManagingFileInformation();
        managingFileInformation.getLoadFileInformation().setFilePath(path);
        managingFileInformation.getLoadFileInformation().loadFile();
    }

    @Test
    void openFile() throws IOException {
        managingFileInformation.getLoadFileInformation().setFilePath("ciao/prova");
        assertEquals("ciao/prova", managingFileInformation.getLoadFileInformation().getFilePath());

        managingFileInformation.getLoadFileInformation().setFilePath(path);
        assertTrue(managingFileInformation.getLoadFileInformation().loadFile());
    }

    @Test
    void getLoadFileInformation() {
        assertEquals(LoadFileInformation.class, managingFileInformation.getLoadFileInformation().getClass());
    }

    @Test
    void printFile() {
        assertFalse(managingFileInformation.getLoadFileInformation().getFileText().isEmpty());
    }
}