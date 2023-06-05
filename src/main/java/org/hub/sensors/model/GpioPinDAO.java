package org.hub.sensors.model;

import java.util.List;

public interface GpioPinDAO {
    GpioPin findById(int id);

    List<GpioPin> findAllIds();

    GpioPin findByGpioPin(int gpioPin);

    List<GpioPin> getAllGpioPins();

    void deleteById(int id);
}
