package org.hub.sensors.service;

import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.GpioPinDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class GpioPinServiceImpl implements GpioPinService{

    @Autowired
    @Qualifier("GpioPinDAOImpl")
    private GpioPinDAO gpioPinDAO;
    @Override
    public GpioPin getById(int id) {
       return gpioPinDAO.findById(id);

    }

    @Override
    public GpioPin getGpioPin(int gpioPin) {
        return gpioPinDAO.findByGpioPin(gpioPin);
    }

    @Override
    public List<GpioPin> findAllIds() {
        return gpioPinDAO.findAllIds();
    }

    @Override
    public List<GpioPin> getAllGpioPins() {
        return gpioPinDAO.getAllGpioPins();
    }

    @Override
    public void deleteById(int id) {
        gpioPinDAO.deleteById(id);
    }
}
