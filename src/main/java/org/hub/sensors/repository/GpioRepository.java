package org.hub.sensors.repository;

import org.hub.sensors.model.GpioPin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GpioRepository extends JpaRepository<GpioPin, Long> {
    List<GpioPin> findAll();

}
