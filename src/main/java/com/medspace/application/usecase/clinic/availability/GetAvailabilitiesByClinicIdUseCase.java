package com.medspace.application.usecase.clinic.availability;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetAvailabilitiesByClinicIdUseCase {
    @Inject
    ClinicService clinicService;

    public List<GetClinicAvailabilityDTO> execute(Long id) {
        List<ClinicAvailability> clinicAvailabilities =
                clinicService.getAvailabilitiesByClinicId(id);
        List<GetClinicAvailabilityDTO> clinicAvailabilityDTOS = new ArrayList<>();

        for (ClinicAvailability clinicAvailability : clinicAvailabilities) {
            clinicAvailabilityDTOS.add(new GetClinicAvailabilityDTO(clinicAvailability));
        }

        return clinicAvailabilityDTOS;
    }
}
