package com.example.securazeek.functionalities;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.loadingFiles.LoadFileInformation;

import java.io.FileNotFoundException;

public class ManagingFileInformation {

    private LoadFileInformation loadFileInformation;

    public ManagingFileInformation(LoadFileInformation loadFileInformation) {
        this.loadFileInformation = loadFileInformation;
    }

    public void loadFile(String path) throws FileNotFoundException, ReadFileException {
        loadFileInformation.setFilePath(path);
        loadFileInformation.loadFile();
    }

    public String printFile(){
        String fileText = loadFileInformation.getFileText();
        return fileText;
    }
}
