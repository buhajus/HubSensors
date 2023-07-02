package org.hub.sensors.model;

public interface SlaveDAO {


    void setSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName);
}
