package com.medspace.domain.repository;

import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.clinic.ClinicQueryDTO;
import java.util.List;

public interface ClinicRepository {
    public Clinic insertClinic(Clinic clinic);

    public List<Clinic> getAllClinics();

    public Clinic getClinicById(Long id);

    public List<Clinic> getFilteredClinics(ClinicQueryDTO queryFilterDTO);

    public List<Clinic> getClinicsByLandlordId(Long landlordId);

    public void deleteClinicById(Long id);

    public Clinic assignClinicToUser(Long clinicId, Long userId);

    public Clinic updateClinic(Long id, Clinic clinic);

    long countAll();

    long countClinicsByCategory(Clinic.Category category);

}
