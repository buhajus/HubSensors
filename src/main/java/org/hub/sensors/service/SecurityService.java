package org.hub.sensors.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
