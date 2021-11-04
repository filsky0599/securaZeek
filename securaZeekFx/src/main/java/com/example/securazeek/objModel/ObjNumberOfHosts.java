package com.example.securazeek.objModel;

public class ObjNumberOfHosts{

    private int numberOfHosts;
    private String hostsNames;

    public ObjNumberOfHosts(int numberOfHosts, String hostsNames) {
        this.numberOfHosts = numberOfHosts;
        this.hostsNames = hostsNames;
    }

    public int getNumberOfHosts() {
        return numberOfHosts;
    }

    public String getHostsNames() {
        return hostsNames;
    }
}
