package com.medspace.domain.repository;

import com.medspace.domain.model.ClinicAvailability;

import java.util.List;

public interface ClinicAvailabilityRepository {
    public ClinicAvailability insertAvailability(ClinicAvailability clinicAvailability);

    public ClinicAvailability updateAvailability(Long id, ClinicAvailability clinicAvailability);

    public void deleteAvailabilityById(Long id);

    public ClinicAvailability assignAvailabilityToClinic(Long clinicAvailabilityId, Long clinicId);

    public List<ClinicAvailability> getAvailabilitiesByClinicId(Long clinicId);
}
