package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadAbsLongestConnection;
import com.example.securazeek.objModel.ObjAbsLongestConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManagingAbsLongestConnection implements LongConnection{

    private LoadAbsLongestConnection loadAbsLongestConnection;
    private ObservableList<ObjAbsLongestConnection> obsListDisplay;
    private ObservableList<ObjAbsLongestConnection> obsListByIp;
    private ObservableList<ObjAbsLongestConnection> obsListDuration;
    private Map<String, Integer> countConnections;

    public ManagingAbsLongestConnection() {
        loadAbsLongestConnection = new LoadAbsLongestConnection();
        obsListDisplay = FXCollections.observableArrayList();
        obsListByIp = FXCollections.observableArrayList();
        obsListDuration = FXCollections.observableArrayList();
        countConnections = new HashMap<>();
    }

    @Override
    public void openFile(String path) throws IOException {
        loadAbsLongestConnection.setFilePath(path);
        loadAbsLongestConnection.loadFile();
    }

    @Override
    public void displayAllConnections(int connectionsDisplayed) throws TooManyConnections {
        if(connectionsDisplayed > loadAbsLongestConnection.getObjAbsLongestConnections().size()){
            throw new TooManyConnections();
        }else {
            for(int x = 0; x < connectionsDisplayed; x++){
                obsListDisplay.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
            }
        }
    }

    @Override
    public void displayConnectionsByIp(String varSourceIp, String varDestIp){
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getSourceIp().equals(varSourceIp)){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else{
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getSourceIp().equals(varSourceIp) && loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) {
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(!loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getSourceIp().equals(varSourceIp)){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(!loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else{
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(!(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getSourceIp().equals(varSourceIp) && loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDestinationIp().equals(varDestIp))){
                    obsListByIp.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion {
        if(varDuration >= 0 && filterStatus){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDuration() >= varDuration){
                    obsListDuration.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else if(varDuration >= 0){
            for(int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
                if(loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDuration() <= varDuration){
                    obsListDuration.add(loadAbsLongestConnection.getObjAbsLongestConnections().get(x));
                }
            }
        }else {
            throw new NotValidInsertion();
        }
    }

    @Override
    public double connectionsAverage() {
        double average, temp = 0;
        for (int x = 0; x < loadAbsLongestConnection.getObjAbsLongestConnections().size(); x++){
            temp += loadAbsLongestConnection.getObjAbsLongestConnections().get(x).getDuration();
        }
        average = temp/loadAbsLongestConnection.getObjAbsLongestConnections().size();
        return average;
    }

    public void countConnectionsOccurrences(){
        for(ObjAbsLongestConnection l : loadAbsLongestConnection.getObjAbsLongestConnections()){
            Integer count = countConnections.get(l.getSourceIp());
            countConnections.put(l.getSourceIp(), (count == null) ? 1 : count + 1);
        }
    }

    public LoadAbsLongestConnection getLoadAbsLongestConnection() {
        return loadAbsLongestConnection;
    }

    public ObservableList<ObjAbsLongestConnection> getObsListDisplay() {
        return obsListDisplay;
    }

    public ObservableList<ObjAbsLongestConnection> getObsListByIp() {
        return obsListByIp;
    }

    public ObservableList<ObjAbsLongestConnection> getObsListDuration() {
        return obsListDuration;
    }

    public Map<String, Integer> getCountConnections() {
        return countConnections;
    }
}
