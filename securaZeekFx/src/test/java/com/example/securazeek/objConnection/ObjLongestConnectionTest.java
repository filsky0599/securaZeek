package com.example.securazeek.objConnection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjLongestConnectionTest {

    ObjLongestConnection objLongestConnection1 = new ObjLongestConnection("192.168.1.43", "343.554.656.232", 4545.545, 8080, "udp", "ssl");
    ObjLongestConnection objLongestConnection2 = new ObjLongestConnection("127.0.0.1", "124.3.4.2", 2, 80, "tcp", "dns");
    ObjLongestConnection objLongestConnection3 = new ObjLongestConnection("234.567.213.796", "192.121.322.4", 15.34, 124, "udp", "Not Defined");

    @Test
    void getSourceIp() {
        assertEquals("192.168.1.43", objLongestConnection1.getSourceIp());
        assertEquals("127.0.0.1", objLongestConnection2.getSourceIp());
        assertEquals("234.567.213.796", objLongestConnection3.getSourceIp());
    }

    @Test
    void getDestinationIp() {
        assertEquals("343.554.656.232", objLongestConnection1.getDestinationIp());
        assertEquals("124.3.4.2", objLongestConnection2.getDestinationIp());
        assertEquals("192.121.322.4", objLongestConnection3.getDestinationIp());
    }

    @Test
    void getProtocol() {
        assertEquals("udp", objLongestConnection1.getProtocol());
        assertEquals("tcp", objLongestConnection2.getProtocol());
        assertEquals("udp", objLongestConnection3.getProtocol());
    }

    @Test
    void getService() {
        assertEquals("ssl", objLongestConnection1.getService());
        assertEquals("dns", objLongestConnection2.getService());
        assertEquals("Not Defined", objLongestConnection3.getService());
    }

    @Test
    void getPort() {
        assertEquals(8080, objLongestConnection1.getPort());
        assertEquals(80, objLongestConnection2.getPort());
        assertEquals(124, objLongestConnection3.getPort());
    }
}