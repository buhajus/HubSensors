package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class GpioPinDAOImpl implements GpioPinDAO {
    @Override
    public GpioPin findById(int id) {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<GpioPin> gpioPins = entityManager
                .createQuery("SELECT n FROM GpioPin n WHERE n.id = :id")
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return gpioPins.get(0);

    }

    @Override
    public List<GpioPin> findAllIds() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<GpioPin> idList = entityManager
                .createNativeQuery("SELECT id FROM gpio_config")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return idList;

    }

    @Override
    public GpioPin findByGpioPin(int gpioPin) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<GpioPin> gpioPinList = entityManager
                .createNativeQuery("SELECT id FROM GpioPin  WHERE gpio = 1?")
                .setParameter("gpio", gpioPin)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return gpioPinList.get(0);
    }

    @Override
    public List<GpioPin> getAllGpioPins() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<GpioPin> gpioPinList = entityManager
                .createNativeQuery("SELECT gpio FROM gpio_config")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return gpioPinList;
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        GpioPin gpioPin = entityManager.find(GpioPin.class, id);
        entityManager.remove(gpioPin);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
