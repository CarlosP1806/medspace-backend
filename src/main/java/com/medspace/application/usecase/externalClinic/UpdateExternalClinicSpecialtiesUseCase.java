package com.medspace.application.usecase.externalClinic;

import com.medspace.application.service.ExternalClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateExternalClinicSpecialtiesUseCase {

    @Inject
    ExternalClinicService externalClinicService;

    public void execute() {
        externalClinicService.updateSpecialties();
    }
}
