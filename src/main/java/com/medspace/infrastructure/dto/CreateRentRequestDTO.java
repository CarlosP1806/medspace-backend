package com.medspace.infrastructure.dto;

import com.medspace.domain.model.RentRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequestDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long spaceId;

    @NotNull
    private LocalDateTime requestedAt;

    public RentRequest toRentRequest() {
        RentRequest rr = new RentRequest();
        rr.setCustomerId(customerId);
        rr.setSpaceId(spaceId);
        rr.setRequestedAt(requestedAt);
        // status will be set by default in the service or repository
        return rr;
    }
}
