package org.hub.sensors.model;

import java.util.List;

public interface SensorDAO {
    void insertEntity(Sensor sensor);

    Sensor findEntityById(int id);

    List<Sensor> findEntities();

    void updateEntity(Sensor sensor);

    void removeEntityById(int id);

    List<Sensor> getGpio();
}
