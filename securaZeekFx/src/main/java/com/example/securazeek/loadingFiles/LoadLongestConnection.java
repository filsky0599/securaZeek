package com.example.securazeek.loadingFiles;

import com.example.securazeek.objConnection.ObjLongestConnection;
import com.example.securazeek.exceptions.ReadFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadLongestConnection extends AbsLoadInputFile {

    private List<ObjLongestConnection> objLongestConnection;

    public LoadLongestConnection() {
        super();
        objLongestConnection = new ArrayList<>();
    }

    @Override
    public boolean loadFile() throws ReadFileException, FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))){
            String line;

            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, "\t");

                String sourceIP = st.nextToken();
                String destIP = st.nextToken();

                double duration;
                String checkTime = st.nextToken();
                if(checkTime.equals("-")){
                    duration = 0.0;
                }else{
                    duration = Double.parseDouble(checkTime);
                }

                int port = Integer.parseInt(st.nextToken());

                String protocol = "";
                String checkProto = st.nextToken();
                if(checkProto.equals("-")){
                    protocol = "Not Found";
                }else{
                    protocol = checkProto;
                }

                String service;
                String checkServ = st.nextToken();
                if(checkServ.equals("-")){
                    service = "Not Found";
                }else {
                    service = checkServ;
                }

                objLongestConnection.add(new ObjLongestConnection(sourceIP, destIP, duration, port, protocol, service));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new ReadFileException();
        }
        return true;
    }

    public List<ObjLongestConnection> getObjLongestConnection() {
        return objLongestConnection;
    }
}
