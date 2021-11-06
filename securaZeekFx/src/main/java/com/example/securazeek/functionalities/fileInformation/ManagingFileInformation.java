package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.loadingFiles.LoadFileInformation;

import java.io.IOException;

public class ManagingFileInformation implements fileInformation {

    private LoadFileInformation loadFileInformation;

    public ManagingFileInformation() {
        loadFileInformation = new LoadFileInformation();
    }

    @Override
    public void openFile(String path) throws IOException {
        loadFileInformation.setFilePath(path);
        loadFileInformation.loadFile();
    }

    public LoadFileInformation getLoadFileInformation() {
        return loadFileInformation;
    }

    public String printFile(){
        return loadFileInformation.getFileText();
    }
}
