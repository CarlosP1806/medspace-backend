package com.medspace.application.usecase.clinic.photo;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetPhotosByClinicIdUseCase {
    @Inject
    ClinicService clinicService;

    public List<GetClinicPhotoDTO> execute(Long clinicId) {
        List<ClinicPhoto> clinicPhotos = clinicService.listPhotosByClinicId(clinicId);
        List<GetClinicPhotoDTO> clinicPhotoDTOs = new ArrayList<>();

        for (ClinicPhoto clinicPhoto : clinicPhotos) {
            clinicPhotoDTOs.add(new GetClinicPhotoDTO(clinicPhoto));
        }

        return clinicPhotoDTOs;
    }
}
