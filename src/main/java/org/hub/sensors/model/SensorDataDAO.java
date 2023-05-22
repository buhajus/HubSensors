package org.hub.sensors.model;

import java.text.DateFormat;
import java.util.List;

public interface SensorDataDAO {
    void insertEntity(SensorData sensor);

    SensorData findEntityById(int id);

    void insertSensorDataStatus(String dateFormat, String sensorLocation, String sensorName, int status);

    List<SensorData> findEntities();

    void updateEntity(SensorData sensor);

    void removeEntityById(int id);


}
