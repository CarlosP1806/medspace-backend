package com.medspace.infrastructure.dto.rentRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestQueryFilterDTO {
    private Long landlordId;
    private String status;

    public RentRequestQueryFilterDTO(Long landlordId) {
        this.landlordId = landlordId;
    }
}
