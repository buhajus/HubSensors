package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.text.DateFormat;
import java.util.List;

public class SensorDataDAOImpl implements SensorDataDAO {
    /**
     * Method to insert new data to DB
     *
     * @param sensor Sensor class object
     */
    @Override
    public void insertEntity(SensorData sensor) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(sensor);

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

    /**
     * Method to insert data to DB if sensor triggers
     * @param dateFormat type of date
     * @param sensorLocation location of sensor
     * @param sensorName sensor name
     * @param status sensor status
     */
    @Override
    public void insertSensorDataStatus(String dateFormat, String sensorLocation, String sensorName, int status) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("INSERT INTO sensors_data (date, sensor_location, sensor_name, status) VALUES (?,?,?,?)")
                .setParameter(1, dateFormat)
                .setParameter(2, sensorLocation)
                .setParameter(3, sensorName)
                .setParameter(4, status)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    /**
     * Method to show entities from DB
     *
     * @return list of results
     */
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
    /**
     * Method to update entity
     *
     * @param sensor Sensor class object
     */
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
    /**
     * Method to remove entity by it`s id
     *
     * @param id
     */
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
