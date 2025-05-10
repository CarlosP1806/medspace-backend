package com.medspace.application.usecase.clinic.photo;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicPhoto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicPhotoUseCase {
    @Inject
    ClinicService clinicService;

    public ClinicPhoto execute(ClinicPhoto clinicPhoto) {
        return clinicService.createClinicPhoto(clinicPhoto);
    }
}
