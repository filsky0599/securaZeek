package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.loadingFiles.LoadAmountOfData;
import com.example.securazeek.objConnection.ObjAmountOfData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ManagingAmountOfData implements Beacons {

    private LoadAmountOfData loadAmountOfData;
    private ObservableList<ObjAmountOfData> obsListDisplay;
    private ObservableList<ObjAmountOfData> obsListIp;
    private ObservableList<ObjAmountOfData> obsListData;
    private ObservableList<ObjAmountOfData> obsListNumber;
    private Map<String, Double> dataChart;
    private Map<String, Integer> numberChart;

    public ManagingAmountOfData() {
        loadAmountOfData = new LoadAmountOfData();
        obsListDisplay = FXCollections.observableArrayList();
        obsListIp = FXCollections.observableArrayList();
        obsListData = FXCollections.observableArrayList();
        obsListNumber = FXCollections.observableArrayList();
        dataChart = new HashMap<>();
        numberChart = new HashMap<>();
    }

    @Override
    public void openFile(String path) throws FileNotFoundException, ReadFileException {
        loadAmountOfData.setFilePath(path);
        loadAmountOfData.loadFile();
    }

    @Override
    public void displayAllConnections(int connectionsDisplayed) {
        for(int x = 0; x < connectionsDisplayed; x++){
            obsListDisplay.add(loadAmountOfData.getObjAmountOfData().get(x));
        }
    }

    @Override
    public void displayConnectionsByIp(String varSourceIp, String varDestIp) {
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getSourceIp().equals(varSourceIp)){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getSourceIp().equals(varSourceIp) && loadAmountOfData.getObjAmountOfData().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByIpReverse(String varSourceIp, String varDestIp){
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(!loadAmountOfData.getObjAmountOfData().get(x).getSourceIp().equals(varSourceIp)){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(!loadAmountOfData.getObjAmountOfData().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(!(loadAmountOfData.getObjAmountOfData().get(x).getSourceIp().equals(varSourceIp) && loadAmountOfData.getObjAmountOfData().get(x).getDestinationIp().equals(varDestIp))){
                    obsListIp.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByNumber(int varNumberOfConnections, boolean filterStatus) throws NotValidInsertion {
        if(varNumberOfConnections > 0 && filterStatus){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getNumberOfConnections() >= varNumberOfConnections){
                    obsListNumber.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else if(varNumberOfConnections > 0){
            for(int x = 0; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getNumberOfConnections() <= varNumberOfConnections){
                    obsListNumber.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        } else{
            throw new NotValidInsertion();
        }
    }

    public void displayConnectionsByData(double varData, boolean filterStatus) throws NotValidInsertion {
        if(varData >= 0 && filterStatus){
            for(int x = 0 ; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getAmountOfData() >= varData){
                    obsListData.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        }else if(varData >= 0){
            for(int x = 0 ; x < loadAmountOfData.getObjAmountOfData().size(); x++){
                if(loadAmountOfData.getObjAmountOfData().get(x).getAmountOfData() <= varData){
                    obsListData.add(loadAmountOfData.getObjAmountOfData().get(x));
                }
            }
        } else{
            throw new NotValidInsertion();
        }
    }

    public void dataMap(){
        for(ObjAmountOfData l : loadAmountOfData.getObjAmountOfData()){
            if(dataChart.containsKey(l.getSourceIp())){
                double tempData = dataChart.get(l.getSourceIp());
                dataChart.put(l.getSourceIp(), l.getAmountOfData() + tempData);
            }else {
                dataChart.put(l.getSourceIp(), l.getAmountOfData());
            }
        }
    }

    public void numberMap(){
        for(ObjAmountOfData l : loadAmountOfData.getObjAmountOfData()){
            if(!numberChart.containsKey(l.getSourceIp())){
                numberChart.put(l.getSourceIp(), l.getNumberOfConnections());
            }
        }
    }

    public LoadAmountOfData getLoadAmountOfData() {
        return loadAmountOfData;
    }

    public ObservableList<ObjAmountOfData> getObsListDisplay() {
        return obsListDisplay;
    }

    public ObservableList<ObjAmountOfData> getObsListIp() {
        return obsListIp;
    }

    public ObservableList<ObjAmountOfData> getObsListData() {
        return obsListData;
    }

    public ObservableList<ObjAmountOfData> getObsListNumber() {
        return obsListNumber;
    }

    public Map<String, Double> getDataChart() {
        return dataChart;
    }

    public Map<String, Integer> getNumberChart() {
        return numberChart;
    }
}
