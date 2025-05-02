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
public class Clinic {
    public enum Category {
        GENERAL_PURPOSE, DENTIST, PEDIATRIC, PSYCHOLOGICAL, SURGICAL, DERMATOLOGICAL
    }

    private Long id;
    private String displayName;
    private Category category;

    private double pricePerDay;
    private int maxStayDays;

    private String addressStreet;
    private String addressCity;
    private String addressState;
    private String addressZip;
    private String addressCountry;
    private String addressLongitude;
    private String addressLatitude;

    private User landlord;

    private Instant createdAt;
}
