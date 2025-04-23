package com.medspace.infrastructure.dto;

import com.medspace.domain.model.RentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRentRequestDTO {

    private Long id;
    private Long customerId;
    private Long spaceId;
    private LocalDateTime requestedAt;
    private String status;

    public GetRentRequestDTO(RentRequest model) {
        this.id = model.getId();
        this.customerId = model.getCustomerId();
        this.spaceId = model.getSpaceId();
        this.requestedAt = model.getRequestedAt();
        this.status = model.getStatus();
    }
}
