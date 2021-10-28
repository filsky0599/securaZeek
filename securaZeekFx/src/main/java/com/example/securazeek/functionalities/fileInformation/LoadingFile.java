package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.exceptions.ReadFileException;

import java.io.FileNotFoundException;

public interface LoadingFile {

    void openFile(String path) throws FileNotFoundException, ReadFileException;

}
