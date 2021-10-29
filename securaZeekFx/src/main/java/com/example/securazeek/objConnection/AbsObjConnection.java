package com.example.securazeek.objConnection;

public abstract class AbsObjConnection {

    private String sourceIp, destinationIp;

    public AbsObjConnection(String sourceIp, String destinationIp) {
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }
}
