package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.domain.model.ClinicPhoto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.InputStream;

@ApplicationScoped
public class CreateClinicPhotoUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public ClinicPhoto execute(ClinicPhoto clinicPhoto, InputStream photoInputStream) {
        return clinicPhotoService.createPhoto(clinicPhoto, photoInputStream);
    }
}
