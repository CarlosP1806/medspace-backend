package com.medspace.infrastructure.dto.rentRequest;

import com.medspace.domain.model.RentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestQueryFilterDTO {
    private Long userId;
    private RentRequest.Status status;

    public RentRequestQueryFilterDTO(Long userId) {
        this.userId = userId;
    }
}
