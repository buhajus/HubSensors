package org.hub.sensors.model;

public interface PoolDataDAO {
    void insertValues(Double ph, Double ch, Double temp, String dateTime, String deviceName);


}
