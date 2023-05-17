package org.hub.sensors.service;

import org.hub.sensors.model.User;
import org.hub.sensors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Skirtas vartotojo informacijos saugojimui (užkoduoto slaptažodžio ir rolių)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // pagal nutylėjimą naujas vartotojas bus "user" - "admin", saugos sumetimais, galima pakeisti tik per duomenų bazę
        user.setAuthorities("user");

        userRepository.save(user);
    }

    @Override
    // kai registruojamas naujas vartotojas, reikia patikrinti ar nėra duplikatų vartotojo vardui
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
