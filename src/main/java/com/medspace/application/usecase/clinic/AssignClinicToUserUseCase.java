package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignClinicToUserUseCase {
    @Inject
    ClinicService clinicService;

    public Clinic execute(Long clinicId, Long userId) {
        return clinicService.assignLandlord(clinicId, userId);
    }
}
