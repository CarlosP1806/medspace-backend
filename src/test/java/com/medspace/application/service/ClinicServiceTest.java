package com.medspace.application.service;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.domain.repository.UserRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ClinicServiceTest {

    @InjectMock
    ClinicRepository clinicRepository;

    @InjectMock
    UserRepository userRepository;

    @Inject
    ClinicService clinicService;

    private User testUser;
    private Clinic testClinic;

    @BeforeEach
    void setUp() {
        // Setup test specialty
        TenantSpecialty testSpecialty = new TenantSpecialty();
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

        // Setup test clinic
        testClinic = new Clinic();
        testClinic.setId(1L);
        testClinic.setDisplayName("Test Clinic");
        testClinic.setCategory(Clinic.Category.GENERAL_PURPOSE);
        testClinic.setDescription("A clinic for testing");
        testClinic.setPricePerDay(100.0);
        testClinic.setMaxStayDays(30);
        testClinic.setAddressStreet("123 Test St");
        testClinic.setAddressCity("Test City");
        testClinic.setAddressState("TS");
        testClinic.setAddressZip("12345");
        testClinic.setAddressCountry("Test Country");
        testClinic.setSize(10);
        testClinic.setLandlord(testUser);
    }


    @Test
    void testCreateClinic() {
        when(clinicRepository.insertClinic(testClinic)).thenReturn(testClinic);
        Clinic createdClinic = clinicService.createClinic(testClinic);

        assert createdClinic.getDisplayName().equals("Test Clinic");
        assert createdClinic.getLandlord().getFullName().equals("Test User");
        assert createdClinic.getCategory() == Clinic.Category.GENERAL_PURPOSE;
    }

    @Test
    void testValidateClinicOwnership() {
        User testUserNotOwner = new User();

        when(clinicRepository.getClinicById(1L)).thenReturn(testClinic);
        when(userRepository.getUserById(1L)).thenReturn(testUser);
        when(userRepository.getUserById(2L)).thenReturn(testUserNotOwner);

        Boolean isOwner = clinicService.validateClinicOwnership(1L, 1L);
        Boolean isNotOwner = clinicService.validateClinicOwnership(1L, 2L);

        assert isOwner == true;
        assert isNotOwner == false;
    }
}
