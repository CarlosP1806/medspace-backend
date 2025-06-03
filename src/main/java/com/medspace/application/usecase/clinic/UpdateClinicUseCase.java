package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateClinicUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long id, Clinic clinic, Long userId) {
        Boolean isOwner = clinicService.validateClinicOwnership(id, userId);
        if (!isOwner) {
            throw new ForbiddenException("Update unauthorized");
        }

        clinicService.updateClinic(id, clinic);
    }
}
