package com.medspace.domain.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TenantFavoriteClinic {
    private Long id;
    private Clinic clinic;
    private User tenant;
    private Instant createdAt;
}