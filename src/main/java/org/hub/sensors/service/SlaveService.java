package org.hub.sensors.service;

public interface SlaveService {
    void setSlaveData(String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName);

}
