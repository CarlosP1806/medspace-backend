package com.medspace.application.service;

import com.medspace.domain.model.ClinicEquipment;
import com.medspace.domain.repository.ClinicEquipmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ClinicEquipmentService {
    @Inject
    ClinicEquipmentRepository clinicEquipmentRepository;

    public ClinicEquipment createEquipment(ClinicEquipment clinicEquipment) {
        clinicEquipment.setCreatedAt(Instant.now());
        clinicEquipment = clinicEquipmentRepository.insertEquipment(clinicEquipment);
        return clinicEquipment;
    }

    public ClinicEquipment assignEquipmentToClinic(Long clinicEquipmentId, Long clinicId) {
        return clinicEquipmentRepository.assignEquipmentToClinic(clinicEquipmentId, clinicId);
    }

    public List<ClinicEquipment> getEquipmentsByClinicId(Long clinicId) {
        return clinicEquipmentRepository.getEquipmentsByClinicId(clinicId);
    }

    public void deleteEquipmentById(Long id) {
        clinicEquipmentRepository.deleteEquipmentById(id);
    }

    public ClinicEquipment getEquipmentById(Long id) {
        return clinicEquipmentRepository.getEquipmentById(id);
    }
}
