package com.medspace.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.dto.common.WeeklyEarningsDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.*;

@QuarkusTest
class EarningsServiceTest {
    @InjectMock
    ClinicRepository clinicRepository;
    @InjectMock
    RentRequestRepository rentRequestRepository;
    @Inject
    EarningsService earningsService;

    private Clinic testClinic;
    private RentRequest acceptedRequest;

    @BeforeEach
    void setUp() {
        testClinic = new Clinic();
        testClinic.setId(1L);
        testClinic.setPricePerDay(100.0);
        List<Clinic> clinics = List.of(testClinic);
        when(clinicRepository.getClinicsByLandlordId(1L)).thenReturn(clinics);

        acceptedRequest = new RentRequest();
        acceptedRequest.setId(1L);
        acceptedRequest.setStatus(RentRequest.Status.ACCEPTED);
        acceptedRequest.setClinic(testClinic);
        acceptedRequest.setCreatedAt(Instant.now());
        when(rentRequestRepository.findAllRequests()).thenReturn(List.of(acceptedRequest));
    }

    @Test
    void testGetLast4WeeksEarningsForLandlord() {
        List<WeeklyEarningsDTO> earnings = earningsService.getLast4WeeksEarningsForLandlord(1L);
        assertNotNull(earnings);
        assertEquals(4, earnings.size());
        boolean found = earnings.stream().anyMatch(e -> e.getEarnings() == 100.0);
        assertTrue(found);
    }

    @Test
    void testNoClinics() {
        when(clinicRepository.getClinicsByLandlordId(2L)).thenReturn(Collections.emptyList());
        List<WeeklyEarningsDTO> earnings = earningsService.getLast4WeeksEarningsForLandlord(2L);
        assertNotNull(earnings);
        assertTrue(earnings.isEmpty());
    }
}
