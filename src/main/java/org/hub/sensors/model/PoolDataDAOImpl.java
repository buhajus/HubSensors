package org.hub.sensors.model;

import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusSerialTransaction;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersResponse;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PoolDataDAOImpl implements PoolDataDAO {
    @Override
    public void insertValues(Double ph, Double ch, Double temp, String dateTime, String deviceName, Boolean alarm) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

       // Slave slave = new Slave();


        entityManager.createNativeQuery(
                        "INSERT INTO pool_data(chloride, ph, temperature, date_time, device_name, alarm) " +
                                "VALUES (?,?,?,?,?,?)")
                .setParameter(1, ch)
                .setParameter(2, ph)
                .setParameter(3, temp)
                .setParameter(4, dateTime)
                .setParameter(5, deviceName)
                .setParameter(6, alarm)
                .executeUpdate();
        // entityManager.persist(poolData);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
