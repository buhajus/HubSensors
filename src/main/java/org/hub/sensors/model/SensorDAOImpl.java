package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class SensorDAOImpl implements SensorDAO {
    @Override
    public void insertEntity(Sensor sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public Sensor findEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Sensor> sensors = entityManager
                .createQuery("SELECT n FROM Sensor n WHERE n.id = :id")
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return sensors.get(0);


    }

    @Override
    public List<Sensor> findEntities() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Sensor> sensors = entityManager.
                createQuery("SELECT n FROM Sensor n")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return sensors;
    }

    @Override
    public void updateEntity(Sensor sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Sensor sensor1 = entityManager.find(Sensor.class, sensor.getId());
        sensor1.setSensorName(sensor.getSensorName());
        sensor1.setSensorLocation(sensor.getSensorLocation());
        sensor1.setDate(sensor.getDate());
        sensor1.setStatus(sensor.getStatus());

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void removeEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Sensor sensor = entityManager.find(Sensor.class, id);
        entityManager.remove(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
