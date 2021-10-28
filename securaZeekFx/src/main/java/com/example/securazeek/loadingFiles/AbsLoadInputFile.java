package com.example.securazeek.loadingFiles;

public abstract class AbsLoadInputFile implements LoadInputFiles{

    private String filePath;

    public AbsLoadInputFile() {
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
