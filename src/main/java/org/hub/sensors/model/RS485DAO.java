package org.hub.sensors.model;

public interface RS485DAO {


    void insertDataFromSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses);
}
