package com.example.securazeek.loadingFiles;

import com.example.securazeek.objConnection.ObjAmountOfData;
import com.example.securazeek.exceptions.ReadFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadAmountOfData extends AbsLoadInputFile {

    private List<ObjAmountOfData> objAmountOfData;

    public LoadAmountOfData() {
        super();
        objAmountOfData = new ArrayList<>();
    }

    @Override
    public boolean loadFile() throws ReadFileException, FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(getFilePath()))){
            String line;

            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " \t");

                int number = Integer.parseInt(st.nextToken());
                String sourceIP = st.nextToken();
                String destIP = st.nextToken();

                double data;
                String check = st.nextToken();
                if(check.equals("-")){
                    data = 0.0;
                }else{
                    data = Double.parseDouble(check);
                }

                objAmountOfData.add(new ObjAmountOfData(number, sourceIP, destIP, data));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new ReadFileException();
        }
        return true;
    }

    public List<ObjAmountOfData> getObjAmountOfData() {
        return objAmountOfData;
    }
}
