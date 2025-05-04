package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.ClinicEquipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClinicEquipmentDTO {
    private Long id;
    private Long clinicId;
    private Integer quantity;
    private String type;

    public GetClinicEquipmentDTO(ClinicEquipment clinicEquipment) {
        this.id = clinicEquipment.getId();
        this.clinicId = clinicEquipment.getClinic().getId();
        this.quantity = clinicEquipment.getQuantity();
        this.type = clinicEquipment.getType();
    }
}
