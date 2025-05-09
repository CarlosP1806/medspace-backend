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
public class GetRentRequestDTO {

    private Long id;
    private Long tenantId;
    private Long clinicId;
    private Instant createdAt;
    private String comments;
    private RentRequest.Status status;

    public GetRentRequestDTO(RentRequest model) {
        this.id = model.getId();
        this.tenantId = model.getTenant().getId();
        this.clinicId = model.getClinic().getId();
        this.createdAt = model.getCreatedAt();
        this.comments = model.getComments();
        this.status = model.getStatus();
    }
}
