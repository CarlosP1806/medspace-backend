package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.GetClinicDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllClinicsUseCase {
    @Inject
    ClinicService clinicService;

    public List<GetClinicDTO> execute() {
        List<Clinic> clinics = clinicService.getAllClinics();
        return clinics.stream().map(clinic -> {
            Double averageRating = clinicService.getAverageRatingById(clinic.getId());
            return new GetClinicDTO(clinic, averageRating);
        }).toList();
    }
}
