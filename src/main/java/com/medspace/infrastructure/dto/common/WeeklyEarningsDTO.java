package com.medspace.infrastructure.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyEarningsDTO {
    private String week;
    private double earnings;
}
