package com.medspace.application.usecase.clinic.availability;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicAvailability;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicAvailabilityUseCase {
    @Inject
    ClinicService clinicService;

    public ClinicAvailability execute(ClinicAvailability clinicAvailability) {
        return clinicService.createClinicAvailability(clinicAvailability);
    }
}
