package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicEquipment;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteClinicEquipmentByIdUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;
    @Inject
    ClinicService clinicService;

    @Transactional
    public void execute(Long id, Long userId) {
        ClinicEquipment clinicEquipment = clinicEquipmentService.getEquipmentById(id);
        Long clinicId = clinicEquipment != null ? clinicEquipment.getClinic().getId() : null;
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Delete unauthorized");
        }

        clinicEquipmentService.deleteEquipmentById(id);
    }
}
