package com.example.securazeek.objConnection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjNumberOfConnectionsTest {

    ObjNumberOfConnections objNumberOfConnections1 = new ObjNumberOfConnections(332, "192.232.564.767", "124.0.0.1");
    ObjNumberOfConnections objNumberOfConnections2 = new ObjNumberOfConnections(1, "192.676.543.743", "464.534.567.343");
    ObjNumberOfConnections objNumberOfConnections3 = new ObjNumberOfConnections(35, "192.434.235.878", "127.232.232.1");

    @Test
    void getNumberOfConnections() {
        assertEquals(332, objNumberOfConnections1.getNumberOfConnections());
        assertEquals(1, objNumberOfConnections2.getNumberOfConnections());
        assertEquals(35, objNumberOfConnections3.getNumberOfConnections());
    }
}