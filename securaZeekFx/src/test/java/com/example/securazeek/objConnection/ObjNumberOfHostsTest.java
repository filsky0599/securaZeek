package com.example.securazeek.objConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjNumberOfHostsTest {

    ObjNumberOfHosts objNumberOfHosts1 = new ObjNumberOfHosts(33, "microsoft.it");
    ObjNumberOfHosts objNumberOfHosts2 = new ObjNumberOfHosts(13, "amazon.it");
    ObjNumberOfHosts objNumberOfHosts3 = new ObjNumberOfHosts(2, "apple.com");

    @Test
    void getNumberOfHosts() {

        assertEquals(33, objNumberOfHosts1.getNumberOfHosts());
        assertEquals(13, objNumberOfHosts2.getNumberOfHosts());
        assertEquals(2, objNumberOfHosts3.getNumberOfHosts());
    }

    @Test
    void getHostsNames() {
        assertEquals("microsoft.it", objNumberOfHosts1.getHostsNames());
        assertEquals("amazon.it", objNumberOfHosts2.getHostsNames());
        assertEquals("apple.com", objNumberOfHosts3.getHostsNames());
    }
}