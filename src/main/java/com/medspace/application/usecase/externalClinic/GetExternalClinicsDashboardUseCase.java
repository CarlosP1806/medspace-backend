package com.medspace.application.usecase.externalClinic;

import com.medspace.application.service.ExternalClinicService;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetExternalClinicsDashboardUseCase {

    @Inject
    ExternalClinicService externalClinicService;

    public List<GetExternalClinicSpecialistsDashboardDTO> executeGetAll() {
        return externalClinicService.getDashboardDataAll();
    }

    public List<GetExternalClinicSpecialistsDashboardDTO> executeWithPagination(
            PaginationDTO pagination) {
        return externalClinicService.getDashboardDataWithPagination(pagination);
    }
}
