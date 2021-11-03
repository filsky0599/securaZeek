package com.example.securazeek.functionalities.dns;

import com.example.securazeek.exceptions.NotValidInsertion;
import com.example.securazeek.exceptions.TooManyConnections;
import com.example.securazeek.loadingFiles.LoadNumberOfHosts;
import com.example.securazeek.objConnection.ObjNumberOfHosts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManagingNumberOfHosts implements Dns {

    private LoadNumberOfHosts loadNumberOfHosts;
    private ObservableList<ObjNumberOfHosts> obsListDisplay;
    private ObservableList<ObjNumberOfHosts> obsListNumberHosts;
    private ObservableList<ObjNumberOfHosts> obsListHosts;
    private Map<String, Integer> mapNumberHosts;

    public ManagingNumberOfHosts() {
        loadNumberOfHosts = new LoadNumberOfHosts();
        obsListDisplay = FXCollections.observableArrayList();
        obsListNumberHosts = FXCollections.observableArrayList();
        obsListHosts = FXCollections.observableArrayList();
        mapNumberHosts = new HashMap<>();
    }

    @Override
    public void openFile(String path) throws IOException {
        loadNumberOfHosts.setFilePath(path);
        loadNumberOfHosts.loadFile();
    }

    @Override
    public void displayAllHosts(int hostsDisplayed) throws TooManyConnections {
        if(hostsDisplayed > loadNumberOfHosts.getObjNumberOfHosts().size()){
            throw new TooManyConnections();
        }
        for(int x = 0; x < hostsDisplayed; x++){
            obsListDisplay.add(loadNumberOfHosts.getObjNumberOfHosts().get(x));
        }
    }

    @Override
    public void displayByHostNumber(int varNumberHosts, boolean filterStatus) throws NotValidInsertion {
        if(varNumberHosts > 0 && filterStatus){
            for (int x = 0; x < loadNumberOfHosts.getObjNumberOfHosts().size(); x++){
                if(loadNumberOfHosts.getObjNumberOfHosts().get(x).getNumberOfHosts() >= varNumberHosts){
                    obsListNumberHosts.add(loadNumberOfHosts.getObjNumberOfHosts().get(x));
                }
            }
        }else if(varNumberHosts > 0){
            for (int x = 0; x < loadNumberOfHosts.getObjNumberOfHosts().size(); x++){
                if(loadNumberOfHosts.getObjNumberOfHosts().get(x).getNumberOfHosts() <= varNumberHosts){
                    obsListNumberHosts.add(loadNumberOfHosts.getObjNumberOfHosts().get(x));
                }
            }
        }
        else{
            throw new NotValidInsertion();
        }
    }

    @Override
    public void displayByHosts(String varHosts, boolean filterStatus){
        if(filterStatus){
            for(int x = 0; x < loadNumberOfHosts.getObjNumberOfHosts().size(); x++){
                if(!loadNumberOfHosts.getObjNumberOfHosts().get(x).getHostsNames().equals(varHosts)){
                    obsListHosts.add(loadNumberOfHosts.getObjNumberOfHosts().get(x));
                }
            }
        }else {
            for(int x = 0; x < loadNumberOfHosts.getObjNumberOfHosts().size(); x++){
                if(loadNumberOfHosts.getObjNumberOfHosts().get(x).getHostsNames().equals(varHosts)){
                    obsListHosts.add(loadNumberOfHosts.getObjNumberOfHosts().get(x));
                }
            }
        }
    }

    public void countMapNumberHosts(){
        for(int x = 0; x < loadNumberOfHosts.getObjNumberOfHosts().size(); x++){
            mapNumberHosts.put(loadNumberOfHosts.getObjNumberOfHosts().get(x).getHostsNames(), loadNumberOfHosts.getObjNumberOfHosts().get(x).getNumberOfHosts());
        }
    }

    public LoadNumberOfHosts getLoadNumberOfHosts() {
        return loadNumberOfHosts;
    }

    public ObservableList<ObjNumberOfHosts> getObsListDisplay() {
        return obsListDisplay;
    }

    public ObservableList<ObjNumberOfHosts> getObsListNumberHosts() {
        return obsListNumberHosts;
    }

    public ObservableList<ObjNumberOfHosts> getObsListHosts() {
        return obsListHosts;
    }

    public Map<String, Integer> getMapNumberHosts() {
        return mapNumberHosts;
    }
}
