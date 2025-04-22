package com.medspace.domain.repository;

import com.medspace.domain.model.Clinic;

import java.util.List;

public interface ClinicRepository {
    public Clinic insertClinic(Clinic clinic);

    public List<Clinic> getAllClinics();

    public Clinic getClinicById(Long id);

    public void deleteClinicById(Long id);

    public Clinic assignClinicToUser(Long clinicId, Long userId);
}
