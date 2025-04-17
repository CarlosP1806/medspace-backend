package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteClinicByIdUseCase {
    @Inject
    ClinicService clinicService;

    public void execute(Long id) {
        clinicService.deleteClinicById(id);
    }
}
