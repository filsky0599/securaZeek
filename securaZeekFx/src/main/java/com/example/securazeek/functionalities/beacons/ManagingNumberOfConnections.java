package com.example.securazeek.functionalities.beacons;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadNumberOfConnections;
import com.example.securazeek.objConnection.ObjNumberOfConnections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManagingNumberOfConnections implements Beacons {

    private LoadNumberOfConnections loadNumberOfConnections;
    private ObservableList<ObjNumberOfConnections> obsListDisplay;
    private ObservableList<ObjNumberOfConnections> obsListByIP;
    private ObservableList<ObjNumberOfConnections> obsListByNumberConnections;
    private Map<String, Integer> countIpMap;

    public ManagingNumberOfConnections() {
        loadNumberOfConnections = new LoadNumberOfConnections();
        obsListDisplay = FXCollections.observableArrayList();
        obsListByIP = FXCollections.observableArrayList();
        obsListByNumberConnections = FXCollections.observableArrayList();
        countIpMap = new HashMap<>();
    }

    @Override
    public void openFile(String path) throws IOException {
        loadNumberOfConnections.setFilePath(path);
        loadNumberOfConnections.loadFile();
    }

    @Override
    public void displayAllConnections(int connectionsDisplayed) throws TooManyConnections {
        if(connectionsDisplayed > loadNumberOfConnections.getObjNumberOfConnections().size()){
            throw new TooManyConnections();
        }else {
            for(int x = 0; x < connectionsDisplayed; x++){
                obsListDisplay.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
            }
        }
    }

    @Override
    public void displayConnectionsByIp(String varSourceIp, String varDestIp) {
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(loadNumberOfConnections.getObjNumberOfConnections().get(x).getSourceIp().equals(varSourceIp)){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(loadNumberOfConnections.getObjNumberOfConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(loadNumberOfConnections.getObjNumberOfConnections().get(x).getSourceIp().equals(varSourceIp) && loadNumberOfConnections.getObjNumberOfConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByIpReverse(String varSourceIp, String varDestIp){
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(!loadNumberOfConnections.getObjNumberOfConnections().get(x).getSourceIp().equals(varSourceIp)){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(!loadNumberOfConnections.getObjNumberOfConnections().get(x).getDestinationIp().equals(varDestIp)){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(!(loadNumberOfConnections.getObjNumberOfConnections().get(x).getSourceIp().equals(varSourceIp) && loadNumberOfConnections.getObjNumberOfConnections().get(x).getDestinationIp().equals(varDestIp))){
                    obsListByIP.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByNumber(int varNumberOfConnections, boolean filterStatus) throws NotValidInsertion {
        if(varNumberOfConnections > 0 && filterStatus){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(loadNumberOfConnections.getObjNumberOfConnections().get(x).getNumberOfConnections() >= varNumberOfConnections){
                    obsListByNumberConnections.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        }else if(varNumberOfConnections > 0){
            for(int x = 0; x < loadNumberOfConnections.getObjNumberOfConnections().size(); x++){
                if(loadNumberOfConnections.getObjNumberOfConnections().get(x).getNumberOfConnections() <= varNumberOfConnections){
                    obsListByNumberConnections.add(loadNumberOfConnections.getObjNumberOfConnections().get(x));
                }
            }
        } else{
            throw new NotValidInsertion();
        }
    }

    public void countIp(){
        for(ObjNumberOfConnections l : loadNumberOfConnections.getObjNumberOfConnections()){
            Integer count = countIpMap.get(l.getSourceIp());
            countIpMap.put(l.getSourceIp(), (count == null) ? 1 : count + 1);
        }
    }

    public LoadNumberOfConnections getLoadNumberOfConnections() {
        return loadNumberOfConnections;
    }

    public ObservableList<ObjNumberOfConnections> getObsListDisplay() {
        return obsListDisplay;
    }

    public ObservableList<ObjNumberOfConnections> getObsListByIP() {
        return obsListByIP;
    }

    public ObservableList<ObjNumberOfConnections> getObsListByNumberConnections() {
        return obsListByNumberConnections;
    }

    public Map<String, Integer> getCountIpMap() {
        return countIpMap;
    }
}
