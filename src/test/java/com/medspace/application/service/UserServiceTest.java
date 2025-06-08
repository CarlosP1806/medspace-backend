package com.medspace.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.UserRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import jakarta.inject.Inject;

@QuarkusTest
class UserServiceTest {

    @InjectMock
    UserRepository userRepository;


    @Inject
    UserService userService;

    private User testUser;
    private TenantSpecialty testSpecialty;

    @BeforeEach
    void setUp() {
        // Setup test specialty
        testSpecialty = new TenantSpecialty();
        testSpecialty.setId(1L);
        testSpecialty.setName("Cardiology");

        // Setup test user
        testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setFirebaseUid("firebase-uid-123");
        testUser.setPhoneNumber("1234567890");
        testUser.setPfpPath("profile-pic.jpg");
        testUser.setBio("Test bio");
        testUser.setUserType(User.UserType.TENANT);
        testUser.setTenantSpecialty(testSpecialty);
        testUser.setTenantLicensePath("license.pdf");
    }


    @Test
    void testCreateTenantUser() {
        testUser.setUserType(User.UserType.TENANT);
        testUser.setTenantSpecialty(testSpecialty);
        testUser.setTenantLicensePath("license.pdf");

        when(userRepository.getUserById(1L)).thenReturn(testUser);

        userService.createUser(testUser);
        User u = userService.getUserById(1L);

        assert u.getUserType() == User.UserType.TENANT;
    }


    @Test
    void testCreateLandlordUser() {
        testUser.setUserType(User.UserType.LANDLORD);
        testUser.setTenantSpecialty(null);
        testUser.setTenantLicensePath(null);

        when(userRepository.getUserById(1L)).thenReturn(testUser);

        userService.createUser(testUser);
        User u = userService.getUserById(1L);

        assert u.getUserType() == User.UserType.LANDLORD;
    }

    @Test
    void testCreateAnalystUser() {
        testUser.setUserType(User.UserType.ANALYST);
        testUser.setTenantSpecialty(null);
        testUser.setTenantLicensePath(null);

        when(userRepository.getUserById(1L)).thenReturn(testUser);

        userService.createUser(testUser);
        User u = userService.getUserById(1L);

        assert u.getUserType() == User.UserType.ANALYST;
    }



    @Test
    void testCreateUserDuplicate() {
        User existingUser = new User();
        existingUser.setFirebaseUid(testUser.getFirebaseUid());
        when(userRepository.getUserByFirebaseId(testUser.getFirebaseUid()))
                .thenReturn(existingUser);

        assertThrows(RuntimeException.class, () -> userService.createUser(testUser));
    }

}
