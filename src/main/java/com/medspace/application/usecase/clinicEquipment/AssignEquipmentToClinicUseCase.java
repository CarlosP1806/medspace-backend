package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.domain.model.ClinicEquipment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignEquipmentToClinicUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;

    public ClinicEquipment execute(Long clinicEquipmentId, Long clinicId) {
        return clinicEquipmentService.assignEquipmentToClinic(clinicEquipmentId, clinicId);
    }
}
