package org.hub.sensors.service;

import org.hub.sensors.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getAll();

    User getById(int id);

    void update(User user);

    void delete(int id);
}
