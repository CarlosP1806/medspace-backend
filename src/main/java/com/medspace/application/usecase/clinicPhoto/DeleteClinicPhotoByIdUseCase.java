package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicPhoto;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteClinicPhotoByIdUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long photoId, Long userId) {
        ClinicPhoto clinicPhoto = clinicPhotoService.getPhotoById(photoId);
        Long clinicId = clinicPhoto != null ? clinicPhoto.getClinic().getId() : null;
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Delete unauthorized");
        }

        clinicPhotoService.deletePhoto(photoId);
    }
}
