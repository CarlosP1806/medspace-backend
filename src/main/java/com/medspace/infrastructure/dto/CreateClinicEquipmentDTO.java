package com.medspace.infrastructure.dto;

import com.medspace.domain.model.ClinicEquipment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClinicEquipmentDTO {
    @NotNull
    private Long clinicId;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String type;

    public ClinicEquipment toClinicEquipment() {
        ClinicEquipment clinicEquipment = new ClinicEquipment();
        clinicEquipment.setType(type);
        clinicEquipment.setQuantity(quantity);
        return clinicEquipment;
    }
}
