package org.hub.sensors.model;

import org.hub.sensors.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    /**
     * Method to insert new data to DB
     *
     * @param user Sensor class object
     */
    @Override
    public void insertEntity(User user) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Method to find entity by it`s id
     *
     * @param id
     * @return 1st result from list
     */

    @Override
    public User findEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager
                .createQuery("SELECT n FROM User n WHERE n.id = :id")
                .setParameter("id", id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return users.get(0);
    }

    /**
     * Method to show entities from DB
     *
     * @return list of results
     */
    @Override
    public List<User> findEntities() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<User> users = entityManager.
                createQuery("SELECT n FROM User n")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return users;
    }

    /**
     * Method to update entity
     *
     * @param user Sensor class object
     */
    @Override
    public void updateEntity(User user) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User user1 = entityManager.find(User.class, user.getId());
        user1.setUsername(user.getUsername());
        user1.setAuthorities(user.getAuthorities());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Method to remove entity by it`s id
     *
     * @param id
     */

    @Override
    public void removeEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
