package com.medspace.application.service;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.domain.repository.ExternalClinicRepository;
import com.medspace.infrastructure.client.ExternalClinicApiClient;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ExternalClinicService {

    @Inject
    ExternalClinicRepository repository;

    @Inject
    ExternalClinicApiClient apiClient;

    public List<GetExternalClinicSpecialistsDashboardDTO> getDashboardDataWithPagination(
            PaginationDTO pagination) {
        return repository.getExternalClinicSpecialistsDashboardData(pagination);
    }

    public List<GetExternalClinicSpecialistsDashboardDTO> getDashboardDataAll() {
        return repository.getAllExternalClinicSpecialistsDashboardData();
    }

    @Transactional
    public void fetchAndSaveClinics() {
        List<ExternalClinic> clinics = apiClient.fetchClinics();
        for (ExternalClinic clinic : clinics) {
            clinic.setCreatedAt(Instant.now());
        }
        repository.saveAll(clinics);
    }

    @Transactional
    public void updateSpecialties() {
        repository.updateSpecialties();
    }
}
