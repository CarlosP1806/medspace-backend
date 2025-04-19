package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.domain.model.ClinicEquipment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicEquipmentUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;

    public ClinicEquipment execute(ClinicEquipment clinicEquipment) {
        return clinicEquipmentService.createEquipment(clinicEquipment);
    }
}
