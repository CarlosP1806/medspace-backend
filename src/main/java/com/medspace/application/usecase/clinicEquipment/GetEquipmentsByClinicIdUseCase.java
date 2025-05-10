package com.medspace.application.usecase.clinicEquipment;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.ClinicEquipment;
import com.medspace.infrastructure.dto.clinic.GetClinicEquipmentDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetEquipmentsByClinicIdUseCase {
    @Inject
    ClinicService clinicService;

    public List<GetClinicEquipmentDTO> execute(Long id) {
        List<ClinicEquipment> clinicEquipments = clinicService.getEquipmentsByClinicId(id);
        List<GetClinicEquipmentDTO> clinicEquipmentDTOs = new ArrayList<>();

        for (ClinicEquipment clinicEquipment : clinicEquipments) {
            clinicEquipmentDTOs.add(new GetClinicEquipmentDTO(clinicEquipment));
        }

        return clinicEquipmentDTOs;
    }
}
