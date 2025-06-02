package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetClinicCountByCategoryUseCase {
    @Inject
    ClinicService clinicService;

    public long execute(Clinic.Category category) {
        return clinicService.countClinicsByCategory(category);
    }
}
