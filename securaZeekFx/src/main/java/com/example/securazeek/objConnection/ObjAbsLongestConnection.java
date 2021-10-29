package com.example.securazeek.objConnection;

public class ObjAbsLongestConnection extends AbsObjConnection {

    private double duration;

    public ObjAbsLongestConnection(String sourceIp, String destinationIp, double duration) {
        super(sourceIp, destinationIp);
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }
}
