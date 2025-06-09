package com.medspace.application.service;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.domain.repository.UserRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class RentServiceTest {

    @InjectMock
    RentRequestRepository rentRequestRepository;

    @InjectMock
    UserRepository userRepository;

    @Inject
    RentService rentService;

    private User testUser;
    private RentRequest testRentRequest;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Test User");
        testUser.setUserType(User.UserType.TENANT);

        testRentRequest = new RentRequest();
        testRentRequest.setId(1L);
        testRentRequest.setTenant(testUser);
        testRentRequest.setStatus(RentRequest.Status.PENDING);
    }

    @Test
    void testCreateRentRequest() {
        when(rentRequestRepository.insert(testRentRequest, 1L, 1L)).thenReturn(testRentRequest);
        RentRequest created = rentService.createRentRequest(testRentRequest, 1L, 1L);
        assert created.getTenant().getFullName().equals("Test User");
        assert created.getStatus() == RentRequest.Status.PENDING;
    }

    @Test
    void testIsRentRequestPending() {
        when(rentRequestRepository.findRequestById(1L)).thenReturn(testRentRequest);
        Boolean pending = rentService.isRentRequestPending(1L);
        assert pending;
    }
}
