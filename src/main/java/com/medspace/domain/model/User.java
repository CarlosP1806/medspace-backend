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
public class User {
    public enum UserType {
        LANDLORD, TENANT, ANALYST,
    }

    private Long id;
    private String fullName;
    private String email;
    private String passwordHash;
    private String profilePictureUrl;
    private String phoneNumber;
    private Instant createdAt;
    private UserType userType;
    private TenantSpecialty tenantSpecialty;
    private String tenantProfessionalLicenseUrl;
    private float averageRating;
    private String stripeCustomerId;
    private String defaultPaymentMethod;
}


