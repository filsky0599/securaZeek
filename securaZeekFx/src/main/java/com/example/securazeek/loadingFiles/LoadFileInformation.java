package com.example.securazeek.loadingFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadFileInformation extends AbsLoadInputFile {

    private String fileText;

    public LoadFileInformation() {
        super();
    }

    @Override
    public boolean loadFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileText = sb.toString();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
        return true;
    }

    public String getFileText() {
        return fileText;
    }
}
