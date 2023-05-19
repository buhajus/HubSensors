package org.hub.sensors.service;

import org.hub.sensors.model.SensorData;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface SensorDataService {

    List<SensorData> getAll();

    void save(SensorData sensor);

    SensorData getById(int id);

    void update(SensorData sensor);

    void delete(int id);

    void insertSensorDataStatus(String dateFormat, String sensorLocation, String sensorName, int status);


}
