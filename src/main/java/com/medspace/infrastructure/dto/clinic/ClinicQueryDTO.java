package com.medspace.infrastructure.dto.clinic;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicQueryDTO {
    // Filters to determine shape of the response
    private Boolean includePhotos;
    private Boolean includeEquipments;
    private Boolean includeAvailabilities;

    // Optional filters to be used in the query
    private Date targetDate;
    private List<String> equipmentList;
    private LocalTime targetHour;
    private String targetCity;

    public ClinicQueryDTO(Boolean includePhotos, Boolean includeEquipments,
            Boolean includeAvailabilities) {
        this.includePhotos = includePhotos;
        this.includeEquipments = includeEquipments;
        this.includeAvailabilities = includeAvailabilities;
    }
}
