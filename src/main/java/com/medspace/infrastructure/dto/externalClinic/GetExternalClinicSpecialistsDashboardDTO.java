package com.medspace.infrastructure.dto.externalClinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExternalClinicSpecialistsDashboardDTO {
    private Double latitud;
    private Double longitud;
    private String clinicBorough;
    private String claseActividad;
    private String specialty;
}
