package com.medspace.application.service;

import java.time.Instant;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.TenantFavoriteClinicRepository;
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
    @Inject
    TenantFavoriteClinicRepository tenantFavoriteClinicRepository;

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

    @Transactional
    public void updateUserById(Long id, User user) {
        userRepository.updateUserById(id, user);
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

    // Favorite clinic methods

    public TenantFavoriteClinic createTenantFavoriteClinic(TenantFavoriteClinic favoriteClinic) {
        return tenantFavoriteClinicRepository.createTenantFavoriteClinic(favoriteClinic);
    }

    public TenantFavoriteClinic getTenantFavoriteClinicById(Long id) {
        return tenantFavoriteClinicRepository.getFavoriteClinicById(id);
    }

    public List<TenantFavoriteClinic> getFavoriteClinicsByTenantId(Long tenantId) {
        return tenantFavoriteClinicRepository.getFavoriteClinicsByTenantId(tenantId);
    }

    public List<TenantFavoriteClinic> getFavoriteClinicsByClinicId(Long clinicId) {
        return tenantFavoriteClinicRepository.getFavoriteClinicsByClinicId(clinicId);
    }

    public void deleteTenantFavoriteClinic(Long tenantId, Long clinicId) {
        tenantFavoriteClinicRepository.removeFavoriteClinic(tenantId, clinicId);
    }

    public boolean isTenantFavoriteClinic(Long tenantId, Long clinicId) {
        return tenantFavoriteClinicRepository.isFavoriteClinic(tenantId, clinicId);
    }

    public TenantFavoriteClinic assignFavoriteClinicToTenant(Long favoriteClinicId, Long tenantId) {
        return tenantFavoriteClinicRepository.assignToTenant(favoriteClinicId, tenantId);
    }

    public TenantFavoriteClinic assignFavoriteClinicToClinic(Long favoriteClinicId, Long clinicId) {
        return tenantFavoriteClinicRepository.assignToClinic(favoriteClinicId, clinicId);
    }

    public Boolean validateTenantFavoriteClinicOwnership(Long favoriteId, Long tenantId) {
        if (favoriteId == null || tenantId == null) {
            return false;
        }
        TenantFavoriteClinic favorite =
                tenantFavoriteClinicRepository.getFavoriteClinicById(favoriteId);
        if (favorite == null || favorite.getTenant() == null) {
            return false;
        }
        return favorite.getTenant().getId().equals(tenantId);
    }
    public long countAllTenants() {
        return userRepository.countByUserType(User.UserType.TENANT);
    }
}
