package org.hub.sensors.model;

import java.util.List;

public interface UserDAO {
    void insertEntity(User user);

    User findEntityById(int id);

    List<User> findEntities();

    void updateEntity(User user);

    void removeEntityById(int id);
}
