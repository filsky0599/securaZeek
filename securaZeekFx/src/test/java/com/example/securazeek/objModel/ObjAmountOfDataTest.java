package com.example.securazeek.objModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjAmountOfDataTest {

    ObjAmountOfData objAmountOfData1 = new ObjAmountOfData(33, "124.424.312.123", "124.4.5.2", 44);
    ObjAmountOfData objAmountOfData2 = new ObjAmountOfData(3021, "132.232.32.3", "32.4.2.65", 0);
    ObjAmountOfData objAmountOfData3 = new ObjAmountOfData(1, "127.0.0.1", "213.323.465.754", 3023230232.34);

    @Test
    void getAmountOfData() {
        assertEquals(44, objAmountOfData1.getAmountOfData());
        assertEquals(0, objAmountOfData2.getAmountOfData());
        assertEquals(3023230232.34, objAmountOfData3.getAmountOfData());
    }

    @Test
    void getSourceIp() {
        assertEquals("124.424.312.123", objAmountOfData1.getSourceIp());
        assertEquals("132.232.32.3", objAmountOfData2.getSourceIp());
        assertEquals("127.0.0.1", objAmountOfData3.getSourceIp());
    }

    @Test
    void getDestinationIp() {
        assertEquals("124.4.5.2", objAmountOfData1.getDestinationIp());
        assertEquals("32.4.2.65", objAmountOfData2.getDestinationIp());
        assertEquals("213.323.465.754", objAmountOfData3.getDestinationIp());
    }
}