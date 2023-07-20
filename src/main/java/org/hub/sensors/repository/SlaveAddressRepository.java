package org.hub.sensors.repository;

import org.hub.sensors.model.SlaveAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaveAddressRepository extends JpaRepository<SlaveAddress, Integer> {
}
