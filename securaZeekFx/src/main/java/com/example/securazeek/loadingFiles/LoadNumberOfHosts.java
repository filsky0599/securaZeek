package com.example.securazeek.loadingFiles;

import com.example.securazeek.objConnection.ObjNumberOfHosts;
import com.example.securazeek.exceptions.ReadFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadNumberOfHosts extends AbsLoadInputFile {

    private List<ObjNumberOfHosts> objNumberOfHosts;

    public LoadNumberOfHosts() {
        super();
        objNumberOfHosts = new ArrayList<>();
    }

    @Override
    public boolean loadFile() throws ReadFileException, FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))){
            String line;

            while ((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " ");

                int number = Integer.parseInt(st.nextToken());
                String host = st.nextToken();

                objNumberOfHosts.add(new ObjNumberOfHosts(number, host));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new ReadFileException();
        }
        return true;
    }

    public List<ObjNumberOfHosts> getObjNumberOfHosts() {
        return objNumberOfHosts;
    }
}
