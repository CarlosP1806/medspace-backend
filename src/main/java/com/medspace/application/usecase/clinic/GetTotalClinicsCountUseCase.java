package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetTotalClinicsCountUseCase {
    @Inject
    ClinicService clinicService;

    public long execute() {
        return clinicService.countAllClinics();
    }
}
