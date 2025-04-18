package com.medspace.application.service;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.repository.ClinicRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ClinicService {
    @Inject
    ClinicRepository clinicRepository;

    public Clinic createClinic(Clinic clinic) {
        clinic.setCreatedAt(Instant.now());
        clinic = clinicRepository.insertClinic(clinic);
        return clinic;
    }

    public Clinic getClinicById(Long id) {
        return clinicRepository.getClinicById(id);
    }

    public void deleteClinicById(Long id) {
        clinicRepository.deleteClinicById(id);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.getAllClinics();
    }
}
