package com.example.securazeek.loadingFiles;

import java.io.IOException;

public interface LoadInputFiles {

    boolean loadFile() throws IOException;
    String getFilePath();
    void setFilePath(String filePath);

}
