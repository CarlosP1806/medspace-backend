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

public class CityFilterDTO {
    public String label; 
    public String value; 
}
