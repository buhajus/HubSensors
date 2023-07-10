package org.hub.sensors.service;

import org.hub.sensors.model.PoolData;
import org.hub.sensors.model.PoolDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoolDataServiceImpl implements PoolDataService {

    @Autowired
    @Qualifier("PoolDataDAOImpl")
    private PoolDataDAO poolDataDAO;

    @Override
    public void save(Double ph, Double ch, Double temp, String dateTime, String deviceName) {
        poolDataDAO.insertValues(ph, ch, temp, dateTime, deviceName);

    }
}
