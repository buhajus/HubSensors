package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SensorDAOImpl implements SensorDAO {
    /**
     * Method to insert new data to DB
     *
     * @param sensor Sensor class object
     */
    @Override
    public void insertEntity(Sensor sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        GpioPin gpioPin = sensor.getGpio();
        GpioPin managedGpioPin = entityManager.merge(gpioPin);
        sensor.setGpio(managedGpioPin);

        entityManager.merge(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    /**
     * Method to find entity by it`s id
     *
     * @param id
     * @return 1st result from list
     */
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

    /**
     * Method to show entities from DB
     *
     * @return list of results
     */
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

    /**
     * Method to update entity
     *
     * @param sensor Sensor class object
     */
    @Override
    public void updateEntity(Sensor sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Sensor sensor1 = entityManager.find(Sensor.class, sensor.getId());
        sensor1.setSensorName(sensor.getSensorName());
        sensor1.setSensorModel(sensor.getSensorModel());
        sensor1.setGpio(sensor.getGpio());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Method to remove entity by it`s id
     *
     * @param id
     */
    @Override
    public void removeEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Sensor sensor = entityManager.find(Sensor.class, id);
        entityManager.remove(sensor);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    @Override
    public List<Sensor> getGpio() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Sensor> gpioList = entityManager
                .createNativeQuery("SELECT id, gpio FROM gpio_config WHERE id NOT IN (SELECT gpio_id FROM sensors)", GpioPin.class)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return gpioList;

    }

    @Override
    public Sensor findGpioByPin(int gPin) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Sensor> sensors = entityManager
                .createNativeQuery("SELECT s.sensor_name FROM sensors AS s" +
                        "LEFT JOIN gpio_config AS gc ON s.gpio_id = gc.id" +
                        "WHERE gc.gpio = ?")
                .setParameter(1, gPin)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return sensors.get(0);
    }
}
