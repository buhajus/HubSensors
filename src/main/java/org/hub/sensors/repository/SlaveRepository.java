package org.hub.sensors.repository;

import org.hub.sensors.model.Slave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaveRepository extends JpaRepository<Slave, Integer> {
}
