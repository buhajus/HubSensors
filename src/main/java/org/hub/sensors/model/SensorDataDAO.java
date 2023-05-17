package org.hub.sensors.model;

import java.util.List;

public interface SensorDataDAO {
    void insertEntity(SensorData sensor);

    SensorData findEntityById(int id);

    List<SensorData> findEntities();

    void updateEntity(SensorData sensor);

    void removeEntityById(int id);
}
