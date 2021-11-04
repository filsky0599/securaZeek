package com.example.securazeek.objModel;

public class ObjLongestConnection extends ObjAbsLongestConnection{

    private String protocol, service;
    private int port;

    public ObjLongestConnection(String sourceIp, String destinationIp, double duration, int port , String protocol, String service) {
        super(sourceIp, destinationIp, duration);
        this.protocol = protocol;
        this.service = service;
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getService() {
        return service;
    }

    public int getPort() {
        return port;
    }
}
