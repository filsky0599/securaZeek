package com.example.securazeek.objModel;

public class ObjNumberOfConnections extends AbsObjConnection {

    private int numberOfConnections;

    public ObjNumberOfConnections(int numberOfConnections, String sourceIp, String destinationIp) {
        super(sourceIp, destinationIp);
        this.numberOfConnections = numberOfConnections;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }
}
