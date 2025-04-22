package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.domain.model.ClinicEquipment;
import com.medspace.infrastructure.dto.GetClinicEquipmentDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetEquipmentsByClinicIdUseCase {
    @Inject
    ClinicEquipmentService clinicEquipmentService;

    public List<GetClinicEquipmentDTO> execute(Long id) {
        List<ClinicEquipment> clinicEquipments = clinicEquipmentService.getEquipmentsByClinicId(id);
        List<GetClinicEquipmentDTO> clinicEquipmentDTOs = new ArrayList<>();

        for (ClinicEquipment clinicEquipment : clinicEquipments) {
            clinicEquipmentDTOs.add(new GetClinicEquipmentDTO(clinicEquipment));
        }

        return clinicEquipmentDTOs;
    }
}
