package com.medspace.application.usecase.clinicAvailability;

import com.medspace.application.service.ClinicAvailabilityService;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.infrastructure.dto.GetClinicAvailabilityDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetAvailabilitiesByClinicIdUseCase {
    @Inject
    ClinicAvailabilityService clinicAvailabilityService;

    public List<GetClinicAvailabilityDTO> execute(Long id) {
        List<ClinicAvailability> clinicAvailabilities =
                clinicAvailabilityService.getAvailabilitiesByClinicId(id);
        List<GetClinicAvailabilityDTO> clinicAvailabilityDTOS = new ArrayList<>();

        for (ClinicAvailability clinicAvailability : clinicAvailabilities) {
            clinicAvailabilityDTOS.add(new GetClinicAvailabilityDTO(clinicAvailability));
        }

        return clinicAvailabilityDTOS;
    }
}
