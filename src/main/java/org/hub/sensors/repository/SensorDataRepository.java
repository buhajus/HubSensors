package org.hub.sensors.repository;

import org.hub.sensors.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

}
