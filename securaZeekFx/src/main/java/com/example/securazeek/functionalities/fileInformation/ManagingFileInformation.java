package com.example.securazeek.functionalities.fileInformation;

import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.functionalities.fileInformation.LoadingFile;
import com.example.securazeek.loadingFiles.LoadFileInformation;

import java.io.FileNotFoundException;

public class ManagingFileInformation implements LoadingFile {

    private LoadFileInformation loadFileInformation;

    public ManagingFileInformation(LoadFileInformation loadFileInformation) {
        this.loadFileInformation = loadFileInformation;
    }

    @Override
    public void openFile(String path) throws FileNotFoundException, ReadFileException {
        loadFileInformation.setFilePath(path);
        loadFileInformation.loadFile();
    }

    public String printFile(){
        return loadFileInformation.getFileText();
    }
}
