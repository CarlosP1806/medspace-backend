package com.medspace.domain.repository;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import java.util.List;

public interface ExternalClinicRepository {
    void saveAll(List<ExternalClinic> clinics);

    List<GetExternalClinicSpecialistsDashboardDTO> getExternalClinicSpecialistsDashboardData(
            PaginationDTO pagination);

    List<GetExternalClinicSpecialistsDashboardDTO> getAllExternalClinicSpecialistsDashboardData();

    void updateSpecialties();
}
