package com.medspace.application.usecase.clinic.photo;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicPhoto;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AssignPhotoToClinicUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public ClinicPhoto execute(Long clinicPhotoId, Long clinicId, Long userId) {
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Assign unauthorized");
        }

        return clinicService.assignPhotoToClinic(clinicPhotoId, clinicId);
    }
}
