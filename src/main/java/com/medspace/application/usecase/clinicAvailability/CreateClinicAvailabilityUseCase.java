package com.medspace.application.usecase.clinicAvailability;

import com.medspace.application.service.ClinicAvailabilityService;
import com.medspace.domain.model.ClinicAvailability;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicAvailabilityUseCase {
    @Inject
    ClinicAvailabilityService clinicAvailabilityService;

    public ClinicAvailability execute(ClinicAvailability clinicAvailability) {
        return clinicAvailabilityService.createAvailability(clinicAvailability);
    }
}
