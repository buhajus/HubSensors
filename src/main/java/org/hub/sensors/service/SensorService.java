package org.hub.sensors.service;

import org.hub.sensors.model.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> getAll();

    void save(Sensor sensor);

    Sensor getById(int id);

    void update(Sensor sensor);

    void delete(int id);
}
