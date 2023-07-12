package org.hub.sensors.service;

import org.hub.sensors.model.PoolData;

public interface PoolDataService {
    void save(Double ph, Double ch, Double temp, String dateTime, String deviceName, Boolean alarm);
}
