package com.example.securazeek.loadingFiles;

public abstract class AbstractLoadInputFile implements LoadInputFiles{

    private String filePath;

    public AbstractLoadInputFile() {
        this.filePath = "";
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
