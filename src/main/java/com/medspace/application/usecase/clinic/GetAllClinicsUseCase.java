package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllClinicsUseCase {
    @Inject
    ClinicService clinicService;

    public List<Clinic> execute() {
        return clinicService.getAllClinics();
    }
}
