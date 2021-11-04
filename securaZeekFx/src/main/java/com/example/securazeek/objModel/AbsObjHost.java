package com.example.securazeek.objModel;

public abstract class AbsObjHost {

    private int numberOfHosts;
    private String hostsNames;

    public AbsObjHost(int numberOfHosts, String hostsNames) {
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
