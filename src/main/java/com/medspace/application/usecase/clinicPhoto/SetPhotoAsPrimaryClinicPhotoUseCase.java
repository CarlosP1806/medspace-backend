package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicService;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SetPhotoAsPrimaryClinicPhotoUseCase {
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long photoId, Long clinicId, Long landlordId) {
        Boolean isPhotoFromClinic = clinicService.validateClinicPhotoOwnership(photoId, clinicId);
        Boolean isClinicFromLandlord = clinicService.validateClinicOwnership(clinicId, landlordId);
        if (!isPhotoFromClinic || !isClinicFromLandlord) {
            throw new ForbiddenException("Update unauthorized");
        }

        clinicService.setClinicPhotoAsPrimary(photoId);
    }
}
