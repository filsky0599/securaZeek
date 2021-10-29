package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.loadingFiles.LoadFileInformation;

import java.io.FileNotFoundException;

public class ManagingFileInformation implements LoadingFile {

    private LoadFileInformation loadFileInformation;

    public ManagingFileInformation() {
        loadFileInformation = new LoadFileInformation();
    }

    @Override
    public void openFile(String path) throws FileNotFoundException, ReadFileException {
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
