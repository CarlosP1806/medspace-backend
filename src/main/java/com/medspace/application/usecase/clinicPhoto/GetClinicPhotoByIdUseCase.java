package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.dto.GetClinicPhotoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetClinicPhotoByIdUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public GetClinicPhotoDTO execute(Long id) {
        ClinicPhoto clinicPhoto = clinicPhotoService.getPhotoById(id);
        return new GetClinicPhotoDTO(clinicPhoto);
    }
}
