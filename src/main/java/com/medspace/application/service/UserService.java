package com.medspace.application.service;

import java.time.Instant;
import java.util.List;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.TenantSpecialtyRepository;
import com.medspace.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    TenantSpecialtyRepository tenantSpecialtyRepository;


    public void createUser(User user) {
        // Hash password
        String rawPassword = user.getPasswordHash(); // fetch temporary raw password from user
        String hashedPassword = rawPassword; // replace later with method to hash password
        user.setPasswordHash(hashedPassword);

        // Set creation timestamp
        user.setCreatedAt(Instant.now());



        userRepository.insertUser(user);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
