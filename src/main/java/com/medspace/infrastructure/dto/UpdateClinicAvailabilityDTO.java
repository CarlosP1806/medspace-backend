package com.medspace.infrastructure.dto;

import com.medspace.domain.model.ClinicAvailability;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClinicAvailabilityDTO {
    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @Pattern(regexp = "MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY",
            message = "WeekDay type must be a day of the week")
    private String weekDay;

    public ClinicAvailability toClinicAvailability() {
        ClinicAvailability clinicAvailability = new ClinicAvailability();

        clinicAvailability.setStartTime(startTime);
        clinicAvailability.setEndTime(endTime);
        clinicAvailability.setWeekDay(ClinicAvailability.WeekDay.valueOf(weekDay));

        return clinicAvailability;
    }
}
