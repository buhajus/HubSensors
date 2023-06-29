package org.hub.sensors.service;

import org.hub.sensors.model.RS485DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RS485ServiceImpl implements RS485Service {

    @Autowired
    @Qualifier("RS485DAOImpl")
    private RS485DAO rs485DAO;

    @Override
    public void insertDataFromSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses) {
        rs485DAO.insertDataFromSlave(portName, slaveId, startAddress, numRegisters, addresses);

    }
}
