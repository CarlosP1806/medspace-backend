package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.ClinicAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClinicAvailabilityDTO {
    private Long id;
    private Long clinicId;
    private LocalTime startTime;
    private LocalTime endTime;
    private ClinicAvailability.WeekDay weekDay;

    public GetClinicAvailabilityDTO(ClinicAvailability clinicAvailability) {
        this.id = clinicAvailability.getId();
        this.clinicId = clinicAvailability.getClinic().getId();
        this.startTime = clinicAvailability.getStartTime();
        this.endTime = clinicAvailability.getEndTime();
        this.weekDay = clinicAvailability.getWeekDay();
    }
}
