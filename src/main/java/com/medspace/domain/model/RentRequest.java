package com.medspace.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentRequest {
    private Long id;
    private User tenant;
    private Clinic clinic;
    private Instant createdAt;
    private String comments;
    private String status;
}
