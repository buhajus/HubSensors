package org.hub.sensors.service;

import org.hub.sensors.model.SensorData;

import java.util.List;

public interface SensorDataService {
    List<SensorData> getAll();

    void save(SensorData sensor);

    SensorData getById(int id);

    void update(SensorData sensor);

    void delete(int id);
}
