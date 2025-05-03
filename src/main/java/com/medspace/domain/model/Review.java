package com.medspace.domain.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {
    private Long id;
    private Type type;

    public enum Type {
        CLINIC, LANDLORD, TENANT,
    }

    // private Long rentAgreementId;
    private User author;
    private Clinic clinic;
    private Integer rating;
    private String comment;
    private Instant createdAt;

}
