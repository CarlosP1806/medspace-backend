package com.medspace.infrastructure.dto.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicQueryFilterDTO {
    private Boolean includePhotos;
    private Boolean includeEquipments;
    private Boolean includeAvailabilities;
}
