package com.example.securazeek.functionalities.connection;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.ReadFileException;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadLongestConnection;
import com.example.securazeek.objConnection.ObjLongestConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ManagingLongestConnection implements LongConnection{

    private LoadLongestConnection loadLongestConnection;
    private ObservableList<ObjLongestConnection> obsListDisplay;
    private ObservableList<ObjLongestConnection> obsListIp;
    private ObservableList<ObjLongestConnection> obsListDuration;
    private ObservableList<ObjLongestConnection> obsListPort;
    private ObservableList<ObjLongestConnection> obsListProtocol;
    private ObservableList<ObjLongestConnection> obsListService;
    private Map<Integer, Integer> countPort;
    private Map<String, Integer> countProtocol;
    private Map<String, Integer> countService;

    public ManagingLongestConnection() {
        loadLongestConnection = new LoadLongestConnection();
        obsListDisplay = FXCollections.observableArrayList();
        obsListIp = FXCollections.observableArrayList();
        obsListDuration = FXCollections.observableArrayList();
        obsListPort = FXCollections.observableArrayList();
        obsListProtocol = FXCollections.observableArrayList();
        obsListService = FXCollections.observableArrayList();
        countPort = new HashMap<>();
        countProtocol = new HashMap<>();
        countService = new HashMap<>();
    }

    @Override
    public void openFile(String path) throws FileNotFoundException, ReadFileException {
        loadLongestConnection.setFilePath(path);
        loadLongestConnection.loadFile();
    }

    @Override
    public void displayAllConnections(int connectionsDisplayed) throws TooManyConnections {
        if(connectionsDisplayed > loadLongestConnection.getObjLongestConnection().size()){
            throw new TooManyConnections();
        }else {
            for(int x = 0; x < connectionsDisplayed; x++){
                obsListDisplay.add(loadLongestConnection.getObjLongestConnection().get(x));
            }
        }
    }

    @Override
    public void displayConnectionsByIp(String varSourceIp, String varDestIp) {
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getSourceIp().equals(varSourceIp)){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getSourceIp().equals(varSourceIp) && loadLongestConnection.getObjLongestConnection().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByIpReverse(String varSourceIp, String varDestIp) {
        if(varDestIp.isEmpty()){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(!loadLongestConnection.getObjLongestConnection().get(x).getSourceIp().equals(varSourceIp)){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else if(varSourceIp.isEmpty()){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(!loadLongestConnection.getObjLongestConnection().get(x).getDestinationIp().equals(varDestIp)){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(!(loadLongestConnection.getObjLongestConnection().get(x).getSourceIp().equals(varSourceIp) && loadLongestConnection.getObjLongestConnection().get(x).getDestinationIp().equals(varDestIp))){
                    obsListIp.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }
    }

    @Override
    public void displayConnectionsByDuration(double varDuration, boolean filterStatus) throws NotValidInsertion {
        if(varDuration >= 0 && filterStatus){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getDuration() >= varDuration){
                    obsListDuration.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else if(varDuration >= 0){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getDuration() <= varDuration){
                    obsListDuration.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        } else {
            throw new NotValidInsertion();
        }
    }

    @Override
    public double connectionsAverage() {
        double average, temp = 0;
        for (int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
            temp += loadLongestConnection.getObjLongestConnection().get(x).getDuration();
        }
        average = temp/loadLongestConnection.getObjLongestConnection().size();
        return average;
    }

    public void displayConnectionsByPort(int varPort, boolean filterStatus) throws NotValidInsertion {
        if(varPort >= 0 && filterStatus){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getPort() != varPort){
                    obsListPort.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else if(varPort >= 0){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getPort() == varPort){
                    obsListPort.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        } else{
            throw new NotValidInsertion();
        }
    }

    public void displayConnectionsByProtocol(String varProtocol, boolean filterStatus){
        if(filterStatus){
            for (int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(!loadLongestConnection.getObjLongestConnection().get(x).getProtocol().equalsIgnoreCase(varProtocol)){
                    obsListProtocol.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else {
            for (int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getProtocol().equalsIgnoreCase(varProtocol)){
                    obsListProtocol.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }
    }

    public void displayConnectionsByService(String varService, boolean filterStatus){
        if(filterStatus){
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(!loadLongestConnection.getObjLongestConnection().get(x).getService().equalsIgnoreCase(varService)){
                    obsListService.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadLongestConnection.getObjLongestConnection().size(); x++){
                if(loadLongestConnection.getObjLongestConnection().get(x).getService().equalsIgnoreCase(varService)){
                    obsListService.add(loadLongestConnection.getObjLongestConnection().get(x));
                }
            }
        }
    }

    public void countPortOccurrences(){
        for(ObjLongestConnection l : loadLongestConnection.getObjLongestConnection()){
            Integer count = countPort.get(l.getPort());
            countPort.put(l.getPort(), (count == null) ? 1 : count + 1);
        }
    }

    public void countProtocolOccurrences(){
        for(ObjLongestConnection l : loadLongestConnection.getObjLongestConnection()){
            Integer count = countProtocol.get(l.getProtocol());
            countProtocol.put(l.getProtocol(), (count == null) ? 1 : count + 1);
        }
    }

    public void countServiceOccurrences(){
        for(ObjLongestConnection l : loadLongestConnection.getObjLongestConnection()){
            Integer count = countService.get(l.getService());
            countService.put(l.getService(), (count == null) ? 1 : count + 1);
        }
    }

    public LoadLongestConnection getLoadLongestConnection() {
        return loadLongestConnection;
    }

    public ObservableList<ObjLongestConnection> getObsListDisplay() {
        return obsListDisplay;
    }

    public ObservableList<ObjLongestConnection> getObsListIp() {
        return obsListIp;
    }

    public ObservableList<ObjLongestConnection> getObsListDuration() {
        return obsListDuration;
    }

    public ObservableList<ObjLongestConnection> getObsListPort() {
        return obsListPort;
    }

    public ObservableList<ObjLongestConnection> getObsListProtocol() {
        return obsListProtocol;
    }

    public ObservableList<ObjLongestConnection> getObsListService() {
        return obsListService;
    }

    public Map<Integer, Integer> getCountPort() {
        return countPort;
    }

    public Map<String, Integer> getCountProtocol() {
        return countProtocol;
    }

    public Map<String, Integer> getCountService() {
        return countService;
    }
}
