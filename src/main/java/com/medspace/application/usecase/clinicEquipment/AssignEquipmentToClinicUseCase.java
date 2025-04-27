package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicEquipment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class AssignEquipmentToClinicUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;
    @Inject
    ClinicService clinicService;

    @Transactional
    public ClinicEquipment execute(Long clinicEquipmentId, Long clinicId, Long userId) {
        Boolean isOwner = clinicService.validateClinicOwnership(clinicId, userId);
        if (!isOwner) {
            throw new ForbiddenException("Assign unauthorized");
        }

        return clinicEquipmentService.assignEquipmentToClinic(clinicEquipmentId, clinicId);
    }
}
