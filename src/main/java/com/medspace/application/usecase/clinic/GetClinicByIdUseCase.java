package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetClinicByIdUseCase {
    @Inject
    ClinicService clinicService;

    public Clinic execute(Long id) {
        Clinic clinic = clinicService.getClinicById(id);
        if (clinic == null) {
            throw new NotFoundException("Clinic with id " + id + " not found");
        }
        return clinic;
    }
}
