package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;

public class SlaveDAOImpl implements SlaveDAO {


    @Override

    public void setSlave(String portName, int slaveId, int startAddress, int numRegisters, int addresses, String deviceName) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("INSERT INTO slave (device_name, port_name, slave_id, start_address, num_registers, addresses) VALUES (?,?,?,?,?,?)")
                .setParameter(1, deviceName)
                .setParameter(2, portName)
                .setParameter(3, slaveId)
                .setParameter(4, startAddress)
                .setParameter(5, numRegisters)
                .setParameter(6, addresses)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}


