package com.medspace.domain.model;

import java.text.NumberFormat.Field;
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
    private String firebaseUid;
    private String profilePictureUrl;
    private String phoneNumber;
    private Instant createdAt;
    private UserType userType;
    private TenantSpecialty tenantSpecialty;
    private String tenantProfessionalLicenseUrl;
    private Float averageRating;
    private String stripeCustomerId;
    private String defaultPaymentMethod;
}


