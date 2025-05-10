package com.medspace.application.usecase.clinicAvailability;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicAvailability;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AssignAvailabilityToClinicUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public ClinicAvailability execute(Long clinicAvailabilityId, Long clinicId, Long userId) {
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Assign unauthorized");
        }

        return clinicService.assignAvailabilityToClinic(clinicAvailabilityId, clinicId);
    }
}
