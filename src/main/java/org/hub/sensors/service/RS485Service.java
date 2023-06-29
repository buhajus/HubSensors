package org.hub.sensors.service;

public interface RS485Service {
    void insertDataFromSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses);

}
