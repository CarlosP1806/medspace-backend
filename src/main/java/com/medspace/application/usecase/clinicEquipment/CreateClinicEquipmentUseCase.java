package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicEquipment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateClinicEquipmentUseCase {
    @Inject
    ClinicService clinicService;

    public ClinicEquipment execute(ClinicEquipment clinicEquipment) {
        return clinicService.createClinicEquipment(clinicEquipment);
    }
}
