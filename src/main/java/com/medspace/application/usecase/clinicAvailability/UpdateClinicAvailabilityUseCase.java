package com.medspace.application.usecase.clinicAvailability;

import com.medspace.application.service.ClinicAvailabilityService;
import com.medspace.domain.model.ClinicAvailability;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateClinicAvailabilityUseCase {
    @Inject
    ClinicAvailabilityService clinicAvailabilityService;

    public ClinicAvailability execute(Long id, ClinicAvailability clinicAvailability) {
        return clinicAvailabilityService.updateAvailabilityById(id, clinicAvailability);
    }
}
