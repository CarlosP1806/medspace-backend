package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.domain.model.ClinicPhoto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicPhotoUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public ClinicPhoto execute(ClinicPhoto clinicPhoto) {
        return clinicPhotoService.createPhoto(clinicPhoto);
    }
}
