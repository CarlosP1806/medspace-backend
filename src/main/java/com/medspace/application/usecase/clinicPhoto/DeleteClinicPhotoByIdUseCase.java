package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteClinicPhotoByIdUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public void execute(Long id) {
        clinicPhotoService.deletePhoto(id);
    }
}
