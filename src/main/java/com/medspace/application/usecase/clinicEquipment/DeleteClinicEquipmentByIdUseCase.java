package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteClinicEquipmentByIdUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;

    public void execute(Long id) {
        clinicEquipmentService.deleteEquipmentById(id);
    }
}
