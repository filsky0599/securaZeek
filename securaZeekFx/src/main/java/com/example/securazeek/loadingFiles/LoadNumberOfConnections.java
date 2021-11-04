package com.example.securazeek.loadingFiles;

import com.example.securazeek.objModel.ObjNumberOfConnections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadNumberOfConnections extends AbsLoadInputFile {

    private List<ObjNumberOfConnections> objNumberOfConnections;

    public LoadNumberOfConnections() {
        super();
        objNumberOfConnections = new ArrayList<>();
    }

    @Override
    public boolean loadFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " \t");

                int number = Integer.parseInt(st.nextToken());
                String sourceIP = st.nextToken();
                String destIP = st.nextToken();

                objNumberOfConnections.add(new ObjNumberOfConnections(number, sourceIP, destIP));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
        return true;
    }

    public List<ObjNumberOfConnections> getObjNumberOfConnections() {
        return objNumberOfConnections;
    }
}
