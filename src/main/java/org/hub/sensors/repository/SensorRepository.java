package org.hub.sensors.repository;

import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Object findAllById(GpioPin[] pins);
}
