package com.medspace.application.service;

import com.medspace.domain.model.ClinicAvailability;
import com.medspace.domain.repository.ClinicAvailabilityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ClinicAvailabilityService {
    @Inject
    ClinicAvailabilityRepository clinicAvailabilityRepository;

    public ClinicAvailability createAvailability(ClinicAvailability clinicAvailability) {
        clinicAvailability.setCreatedAt(Instant.now());
        clinicAvailability = clinicAvailabilityRepository.insertAvailability(clinicAvailability);
        return clinicAvailability;
    }

    public ClinicAvailability assignAvailabilityToClinic(Long clinicAvailabilityId, Long clinicId) {
        return clinicAvailabilityRepository.assignAvailabilityToClinic(clinicAvailabilityId,
                clinicId);
    }

    public List<ClinicAvailability> getAvailabilitiesByClinicId(Long clinicId) {
        return clinicAvailabilityRepository.getAvailabilitiesByClinicId(clinicId);
    }

    public void deleteAvailabilityById(Long id) {
        clinicAvailabilityRepository.deleteAvailabilityById(id);
    }

    public ClinicAvailability updateAvailabilityById(Long id,
            ClinicAvailability clinicAvailability) {
        return clinicAvailabilityRepository.updateAvailability(id, clinicAvailability);
    }
}
