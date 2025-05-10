package com.medspace.application.usecase.clinic.availability;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicAvailability;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteClinicAvailabilityByIdUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long availabilityId, Long userId) {
        ClinicAvailability clinicAvailability =
                clinicService.getClinicAvailabilityById(availabilityId);
        Long clinicId = clinicAvailability != null ? clinicAvailability.getClinic().getId() : null;
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Delete unauthorized");
        }

        clinicService.deleteClinicAvailabilityById(availabilityId);
    }
}
