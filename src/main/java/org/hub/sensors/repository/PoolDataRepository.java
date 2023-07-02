package org.hub.sensors.repository;

import org.hub.sensors.model.PoolData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolDataRepository extends JpaRepository<PoolData, Integer> {
}
