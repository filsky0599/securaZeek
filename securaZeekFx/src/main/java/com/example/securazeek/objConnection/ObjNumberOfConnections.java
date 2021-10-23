package com.example.securazeek.objConnection;

public class ObjNumberOfConnections extends AbsObjconnection{

    private int numberOfConnections;

    public ObjNumberOfConnections(int numberOfConnections, String sourceIp, String destinationIp) {
        super(sourceIp, destinationIp);
        this.numberOfConnections = numberOfConnections;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }
}
