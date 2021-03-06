package com.example.securazeek.loadingFiles;

import com.example.securazeek.objModel.ObjAbsLongestConnection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadAbsLongestConnection extends AbsLoadInputFile {

    private List<ObjAbsLongestConnection> objAbsLongestConnections;

    public LoadAbsLongestConnection() {
        super();
        objAbsLongestConnections = new ArrayList<>();
    }

    @Override
    public boolean loadFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))){
            String line;

            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, "\t");

                String sourceIP = st.nextToken();
                String destIP = st.nextToken();
                double time = Double.parseDouble(st.nextToken());

                objAbsLongestConnections.add(new ObjAbsLongestConnection(sourceIP, destIP, time));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
        return true;
    }

    public List<ObjAbsLongestConnection> getObjAbsLongestConnections() {
        return objAbsLongestConnections;
    }
}
