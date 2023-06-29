package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;

public class RS485DAOImpl implements RS485DAO {


    @Override

    public void insertDataFromSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("INSERT INTO rs485_data (port_name, slave_id, start_address, num_registers, addresses) VALUES (?,?,?,?,?)")
                .setParameter(1, portName)
                .setParameter(2, slaveId)
                .setParameter(3, startAddress)
                .setParameter(4, numRegisters)
                .setParameter(5, addresses)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}


