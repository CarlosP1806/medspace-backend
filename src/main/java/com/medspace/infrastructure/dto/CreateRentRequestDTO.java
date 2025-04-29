package com.medspace.infrastructure.dto;

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
public class CreateRentRequestDTO {

    private Long tenantId;
    private Long clinicId;
    private Instant startDate;
    private Instant endDate;
    private String comments;
    private String status;

    public RentRequest toModel() {
        RentRequest rr = new RentRequest();
        rr.setStartDate(startDate);
        rr.setEndDate(endDate);
        rr.setComments(comments);
        rr.setStatus(status);
        return rr;
    }
}
