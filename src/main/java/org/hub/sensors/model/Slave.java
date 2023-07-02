package org.hub.sensors.model;

import javax.persistence.*;

@Entity
@Table(name = "slave")
public class Slave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "port_name")
    private String portName;
    @Column(name = "slave_id")
    private int slaveId;
    @Column(name = "start_address")
    private int startAddress;
    @Column(name = "num_registers")
    private int numRegisters;
    @Column(name = "addresses")
    private int addresses;
    @Column(name = "deviceName")
    private String deviceName;

    public Slave() {
    }

    public Slave(int id, String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName) {
        this.id = id;
        this.portName = portName;
        this.slaveId = slaveId;
        this.startAddress = startAddress;
        this.numRegisters = numRegisters;
        this.addresses = addresses;
        this.deviceName = deviceName;
    }

    public Slave(String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName) {
        this.portName = portName;
        this.slaveId = slaveId;
        this.startAddress = startAddress;
        this.numRegisters = numRegisters;
        this.addresses = addresses;
        this.deviceName = deviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
