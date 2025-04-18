package com.medspace.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClinicPhoto {
    private Long id;
    private String url;

    private Clinic clinic;

    private Instant createdAt;
}
