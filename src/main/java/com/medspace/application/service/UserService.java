package com.medspace.application.service;

import java.time.Instant;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.TenantSpecialtyRepository;
import com.medspace.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    TenantSpecialtyRepository tenantSpecialtyRepository;

    @Transactional
    public void createUser(User user) {
        try {
            // Optional: Replace with a more efficient check
            if (userRepository.getUserByFirebaseId(user.getFirebaseUid()) != null) {
                throw new RuntimeException(
                        "User already exists with this Firebase UID: " + user.getFirebaseUid());
            }

            // Set creation timestamp
            user.setCreatedAt(Instant.now());

            // Save user to DB
            userRepository.insertUser(user);

        } catch (Exception e) {
            // Cleanup Firebase user if local DB save failed
            try {
                FirebaseAuth.getInstance().deleteUser(user.getFirebaseUid());
            } catch (Exception rollbackEx) {
                System.err.println("Firebase rollback failed: " + rollbackEx.getMessage());
                // In production, use a proper logger
            }

            throw new RuntimeException("User creation failed: " + e.getMessage(), e);
        }
    }

    public User getUserById(Long id) {

        User user = userRepository.getUserById(id);

        if (user == null) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        return user;
    }

    @Transactional
    public void deleteUserById(Long id) {

        User user = getUserById(id);

        try {
            // First delete from Firebase
            FirebaseAuth.getInstance().deleteUser(user.getFirebaseUid());

            // Only delete from the local DB after successful Firebase deletion
            userRepository.deleteUserById(user.getId());

        } catch (Exception exception) {

            throw new RuntimeException("User deletion failed: " + exception.getMessage(),
                    exception);
        }
    }


    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Specialty methods

    public TenantSpecialty getTenantSpecialtyById(Long id) {
        return tenantSpecialtyRepository.getTenantSpecialtyById(id);
    }

    public List<TenantSpecialty> getAllTenantSpecialties() {
        return tenantSpecialtyRepository.getAllTenantSpecialties();
    }
}
