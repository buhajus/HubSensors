package org.hub.sensors.service;

import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service - servisų sluoksnis biznio logikai
// Po serviso sluoksniu kreipiamės į DAO
@Service
public class SensorServiceImpl implements SensorService {
    // autowire- naudojamas automatinei priklausomybių injekcijai
    // Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
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
}
