package com.medspace.infrastructure.dto.rentRequest;

import com.medspace.domain.model.RentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRentRequestSpecialistsDashboardDTO {
    private Long rentRequestId;
    private String tenantName;
    private String clinicName;
    private RentRequest.Status status;
    private Instant createdAt;
    private String tenantSpecialty;
    private String clinicAddress;
    private String clinicBorough;
    private Double clinicLatitude;
    private Double clinicLongitude;
}
