package com.medspace.application.usecase.clinic.availability;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicAvailability;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateClinicAvailabilityUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public ClinicAvailability execute(Long id, ClinicAvailability clinicAvailability, Long userId) {
        ClinicAvailability existingAvailability = clinicService.getClinicAvailabilityById(id);
        Long clinicId =
                existingAvailability != null ? existingAvailability.getClinic().getId() : null;
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Update unauthorized");
        }

        return clinicService.updateClinicAvailabilityById(id, clinicAvailability);
    }
}
