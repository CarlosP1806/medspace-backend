package com.medspace.domain.model;

import java.sql.Date;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentRequestDay {
    private Long id;
    private Date date;
    private RentRequest rentRequest;

    private Instant createdAt;
}
