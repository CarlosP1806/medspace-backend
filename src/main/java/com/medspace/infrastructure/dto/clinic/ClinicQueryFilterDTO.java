package com.medspace.infrastructure.dto.clinic;

import java.sql.Date;
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
    private Date targetDate;

    public ClinicQueryFilterDTO(Boolean includePhotos, Boolean includeEquipments,
            Boolean includeAvailabilities) {
        this.includePhotos = includePhotos;
        this.includeEquipments = includeEquipments;
        this.includeAvailabilities = includeAvailabilities;
    }
}
