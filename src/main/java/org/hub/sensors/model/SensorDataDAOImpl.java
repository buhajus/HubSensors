package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SensorDataDAOImpl implements SensorDataDAO {
    @Override
    public void insertEntity(SensorData sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public SensorData findEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<SensorData> sensors = entityManager
                .createQuery("SELECT n FROM SensorData n WHERE n.id = :id")
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return sensors.get(0);


    }

    @Override
    public List<SensorData> findEntities() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<SensorData> sensors = entityManager.
                createQuery("SELECT n FROM SensorData n")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return sensors;
    }

    @Override
    public void updateEntity(SensorData sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        SensorData sensor1 = entityManager.find(SensorData.class, sensor.getId());
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

        SensorData sensor = entityManager.find(SensorData.class, id);
        entityManager.remove(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
