package org.hub.sensors.service;

import org.hub.sensors.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
