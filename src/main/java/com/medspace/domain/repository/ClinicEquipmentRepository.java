package com.medspace.domain.repository;

import com.medspace.domain.model.ClinicEquipment;

import java.util.List;

public interface ClinicEquipmentRepository {
    public ClinicEquipment insertEquipment(ClinicEquipment clinicEquipment);

    public ClinicEquipment getEquipmentById(Long id);

    public void deleteEquipmentById(Long id);

    public ClinicEquipment assignEquipmentToClinic(Long clinicEquipmentId, Long clinicId);

    public List<ClinicEquipment> getEquipmentsByClinicId(Long clinicId);
}
