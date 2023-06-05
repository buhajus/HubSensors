package org.hub.sensors.repository;

import org.hub.sensors.model.GpioPin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GpioRepository extends JpaRepository<GpioPin, Integer> {
    @Override
    List<GpioPin> findAll();

    @Override
    void deleteById(Integer integer);
    //  @Query(value = "Select gpio FROM gpio_config WHERE id = 1", nativeQuery = true)
    //   GpioPin getOne(int id);
    // List<GpioPin> findAllById(List<Integer> id);

    // GpioPin findAllById(int gpio);
}
