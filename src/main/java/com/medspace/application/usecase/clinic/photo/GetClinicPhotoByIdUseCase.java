package com.medspace.application.usecase.clinic.photo;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetClinicPhotoByIdUseCase {
    @Inject
    ClinicService clinicService;

    public GetClinicPhotoDTO execute(Long id) {
        ClinicPhoto clinicPhoto = clinicService.getClinicPhotoById(id);
        return new GetClinicPhotoDTO(clinicPhoto);
    }
}
