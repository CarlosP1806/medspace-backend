package com.medspace.application.usecase.clinicPhoto;

import com.medspace.application.service.ClinicPhotoService;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.dto.GetPhotoByClinicIdDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetPhotosByClinicIdUseCase {
    @Inject
    ClinicPhotoService clinicPhotoService;

    public List<GetPhotoByClinicIdDTO> execute(Long clinicId) {
        List<ClinicPhoto> clinicPhotos = clinicPhotoService.listPhotosByClinicId(clinicId);
        List<GetPhotoByClinicIdDTO> clinicPhotoDTOs = new ArrayList<>();

        for (ClinicPhoto clinicPhoto : clinicPhotos) {
            clinicPhotoDTOs.add(new GetPhotoByClinicIdDTO(clinicPhoto));
        }

        return clinicPhotoDTOs;
    }
}
