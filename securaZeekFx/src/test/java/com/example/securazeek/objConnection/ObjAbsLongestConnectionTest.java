package com.example.securazeek.objConnection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjAbsLongestConnectionTest {

    ObjAbsLongestConnection objAbsLongestConnection1 = new ObjAbsLongestConnection("192.168.1.43", "343.554.656.232", 4545.545);
    ObjAbsLongestConnection objAbsLongestConnection2 = new ObjAbsLongestConnection("127.0.0.1", "124.3.4.2", 2);
    ObjAbsLongestConnection objAbsLongestConnection3 = new ObjAbsLongestConnection("234.567.213.796", "192.121.322.4", 15.34);
    ObjAbsLongestConnection objAbsLongestConnection4 = new ObjAbsLongestConnection("13.43.2.0", "192.168.2.2", 0);

    @Test
    void getDuration() {
        assertEquals(4545.545, objAbsLongestConnection1.getDuration());
        assertEquals(2, objAbsLongestConnection2.getDuration());
        assertEquals(15.34, objAbsLongestConnection3.getDuration());
        assertEquals(0, objAbsLongestConnection4.getDuration());
    }

    @Test
    void getSourceIp() {
        assertEquals("192.168.1.43", objAbsLongestConnection1.getSourceIp());
        assertEquals("127.0.0.1", objAbsLongestConnection2.getSourceIp());
        assertEquals("234.567.213.796", objAbsLongestConnection3.getSourceIp());
        assertEquals("13.43.2.0", objAbsLongestConnection4.getSourceIp());
    }

    @Test
    void getDestinationIp() {
        assertEquals("343.554.656.232", objAbsLongestConnection1.getDestinationIp());
        assertEquals("124.3.4.2", objAbsLongestConnection2.getDestinationIp());
        assertEquals("192.121.322.4", objAbsLongestConnection3.getDestinationIp());
        assertEquals("192.168.2.2", objAbsLongestConnection4.getDestinationIp());
    }
}