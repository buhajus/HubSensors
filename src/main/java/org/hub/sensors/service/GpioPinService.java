package org.hub.sensors.service;

import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.Sensor;

import java.util.List;

public interface GpioPinService {
    GpioPin getById(int id);

    GpioPin getGpioPin(int gpioPin);

    List<GpioPin> findAllIds();

    List<GpioPin> getAllGpioPins();
    void deleteById(int id);
}
