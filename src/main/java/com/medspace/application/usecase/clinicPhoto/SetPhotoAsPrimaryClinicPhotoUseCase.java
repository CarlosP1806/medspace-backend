package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.application.service.ClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class SetPhotoAsPrimaryClinicPhotoUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long photoId, Long clinicId, Long landlordId) {
        Boolean isPhotoFromClinic = clinicPhotoService.validatePhotoOwnership(photoId, clinicId);
        Boolean isClinicFromLandlord = clinicService.validateClinicOwnership(clinicId, landlordId);
        if (!isPhotoFromClinic || !isClinicFromLandlord) {
            throw new ForbiddenException("Update unauthorized");
        }

        clinicPhotoService.setPhotoAsPrimary(photoId);
    }
}
