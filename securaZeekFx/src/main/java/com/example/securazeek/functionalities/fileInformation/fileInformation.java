package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.functionalities.LoadFile;

import java.io.IOException;

public interface fileInformation extends LoadFile {

    void openFile(String path) throws IOException;

}
