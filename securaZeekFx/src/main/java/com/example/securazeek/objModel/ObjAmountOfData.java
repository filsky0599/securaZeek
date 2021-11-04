package com.example.securazeek.objModel;

public class ObjAmountOfData extends ObjNumberOfConnections{

    private double amountOfData;

    public ObjAmountOfData(int numberOfConnections, String sourceIp, String destinationIp, double amountOfData) {
        super(numberOfConnections, sourceIp, destinationIp);
        this.amountOfData = amountOfData;
    }

    public double getAmountOfData() {
        return amountOfData;
    }
}
