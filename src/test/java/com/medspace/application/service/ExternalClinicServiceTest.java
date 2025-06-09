package com.medspace.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.domain.repository.ExternalClinicRepository;
import com.medspace.infrastructure.client.ExternalClinicApiClient;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import java.util.*;

@QuarkusTest
class ExternalClinicServiceTest {
    @InjectMock
    ExternalClinicRepository repository;

    @InjectMock
    ExternalClinicApiClient apiClient;

    @Inject
    ExternalClinicService service;

    @Test
    void testGetDashboardDataWithPagination() {
        PaginationDTO pagination = new PaginationDTO(0, 10);
        List<GetExternalClinicSpecialistsDashboardDTO> mockList = List.of();
        when(repository.getExternalClinicSpecialistsDashboardData(pagination)).thenReturn(mockList);
        List<GetExternalClinicSpecialistsDashboardDTO> result =
                service.getDashboardDataWithPagination(pagination);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetDashboardDataAll() {
        List<GetExternalClinicSpecialistsDashboardDTO> mockList = List.of();
        when(repository.getAllExternalClinicSpecialistsDashboardData()).thenReturn(mockList);
        List<GetExternalClinicSpecialistsDashboardDTO> result = service.getDashboardDataAll();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testFetchAndSaveClinics() {
        List<ExternalClinic> clinics = new ArrayList<>();
        when(apiClient.fetchClinics()).thenReturn(clinics);
        service.fetchAndSaveClinics();
        verify(repository).saveAll(clinics);
    }

    @Test
    void testUpdateSpecialties() {
        service.updateSpecialties();
        verify(repository).updateSpecialties();
    }
}
