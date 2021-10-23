package com.example.securazeek.loadingFiles;

import com.example.securazeek.exceptions.ReadFileException;

import java.io.FileNotFoundException;

public interface LoadInputFiles {

    boolean loadFile() throws ReadFileException, FileNotFoundException;
    String getFilePath();
    void setFilePath(String filePath);

}
