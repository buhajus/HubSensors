package org.hub.sensors.service;

import org.hub.sensors.model.SlaveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SlaveServiceImpl implements SlaveService {

    @Autowired
    @Qualifier("SlaveDAOImpl")
    private SlaveDAO slaveDAO;

    @Override
    public void setSlaveData(String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName) {
        slaveDAO.setSlave(portName, slaveId, startAddress, numRegisters, addresses, deviceName);

    }
}
