package org.hub.sensors.model;

public class Rs485 {

    private String portName;
    private int slaveId;
    private int startAddress;
    private int numRegisters;
    private int addresses;

    public Rs485() {
    }

    public Rs485(String portName, int slaveId, int startAddress, int numRegisters, int addresses) {
        this.portName = portName;
        this.slaveId = slaveId;
        this.startAddress = startAddress;
        this.numRegisters = numRegisters;
        this.addresses = addresses;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getNumRegisters() {
        return numRegisters;
    }

    public void setNumRegisters(int numRegisters) {
        this.numRegisters = numRegisters;
    }

    public int getAddresses() {
        return addresses;
    }

    public void setAddresses(int addresses) {
        this.addresses = addresses;
    }
}
