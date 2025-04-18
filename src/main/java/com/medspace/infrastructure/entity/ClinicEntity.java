package com.medspace.infrastructure.entity;

import com.medspace.domain.model.Clinic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Clinic.Category category;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "max_stay_days")
    private int maxStayDays;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "address_zip")
    private String addressZip;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "address_longitude")
    private String addressLongitude;

    @Column(name = "address_latitude")
    private String addressLatitude;

    @Column(name = "created_at")
    private Instant createdAt;
}
