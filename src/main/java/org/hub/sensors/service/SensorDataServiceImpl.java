package org.hub.sensors.service;

import org.hub.sensors.model.SensorData;
import org.hub.sensors.model.SensorDataDAO;
import org.hub.sensors.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service - servisų sluoksnis biznio logikai
// Po serviso sluoksniu kreipiamės į DAO
@Service
public class SensorDataServiceImpl implements SensorDataService {
    // autowire- naudojamas automatinei priklausomybių injekcijai
    // Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("SensorDataDAOImpl")
    private SensorDataDAO sensorDataDAO;

    @Autowired
    private SensorDataRepository sensorDataRepository;



    /**
     *
     * @return
     */
    @Override
    public List<SensorData> getAll(PageRequest pageRequest) {
        Page<SensorData> page = sensorDataRepository.findAll(pageRequest);
        return page.getContent();
       // return sensorDataDAO.findEntities();
    }

    @Override
    public void save(SensorData sensor) {
        sensorDataDAO.insertEntity(sensor);

    }

    @Override
    public SensorData getById(int id) {
        return sensorDataDAO.findEntityById(id);
    }

    @Override
    public void update(SensorData sensor) {
        sensorDataDAO.updateEntity(sensor);
    }

    @Override
    public void delete(int id) {
        sensorDataDAO.removeEntityById(id);
    }


    @Override
    public void insertSensorDataStatus(String dateFormat, String sensorLocation, String sensorName, int status) {
        sensorDataDAO.insertSensorDataStatus(dateFormat, sensorLocation, sensorName, status);

    }


}
