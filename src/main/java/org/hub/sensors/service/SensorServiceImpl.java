package org.hub.sensors.service;

import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SensorServiceImpl implements SensorService {

    @Autowired
    @Qualifier("SensorDAOImpl")
    private SensorDAO sensorDAO;

    @Override
    public List<Sensor> getAll() {
        return sensorDAO.findEntities();
    }

    @Override
    public void save(Sensor sensor) {
        sensorDAO.insertEntity(sensor);

    }

    @Override
    public Sensor getById(int id) {
        return sensorDAO.findEntityById(id);

    }

    @Override
    public void update(Sensor sensor) {
        sensorDAO.updateEntity(sensor);

    }

    @Override
    public void delete(int id) {
        sensorDAO.removeEntityById(id);
    }

    @Override
    public  List<Sensor> getGpio(){
        return sensorDAO.getGpio();
    }


}
