package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteClinicByIdUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long id, Long loggedUserId) {
        Boolean isOwner = clinicService.validateClinicOwnership(id, loggedUserId);
        if (!isOwner) {
            throw new ForbiddenException("Delete unauthorized");
        }

        clinicService.deleteClinicById(id);
    }
}
