package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.domain.model.ClinicPhoto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignPhotoToClinicUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public ClinicPhoto execute(Long clinicPhotoId, Long clinicId) {
        return clinicPhotoService.assignPhotoToClinic(clinicPhotoId, clinicId);
    }
}
