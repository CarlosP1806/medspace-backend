package com.medspace.application.usecase.clinicAvailability;

import com.medspace.application.service.ClinicAvailabilityService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteClinicAvailabilityByIdUseCase {
    @Inject
    ClinicAvailabilityService clinicAvailabilityService;

    public void execute(Long id) {
        clinicAvailabilityService.deleteAvailabilityById(id);
    }
}
