package com.medspace.infrastructure.dto.rentRequest;

import com.medspace.domain.model.RentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRentRequestDTO {

    private Long id;
    private Long tenantId;
    private Long clinicId;
    private Instant createdAt;
    private Date startDate;
    private Date endDate;
    private String comments;
    private String status;

    public GetRentRequestDTO(RentRequest model) {
        this.id = model.getId();
        this.tenantId = model.getTenant().getId();
        this.clinicId = model.getClinic().getId();
        this.createdAt = model.getCreatedAt();
        this.startDate = model.getStartDate();
        this.endDate = model.getEndDate();
        this.comments = model.getComments();
        this.status = model.getStatus();
    }
}
