package com.example.securazeek.functionalities;

import com.example.securazeek.exceptions.ReadFileException;

import java.io.FileNotFoundException;

public interface Functionalities {

    void openFile(String path) throws FileNotFoundException, ReadFileException;

}
