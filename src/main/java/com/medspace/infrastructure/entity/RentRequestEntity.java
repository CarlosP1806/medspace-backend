package com.medspace.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import com.medspace.domain.model.RentRequest;

@Getter
@Setter
@Entity
@Table(name = "rent_request")
public class RentRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private UserEntity tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private ClinicEntity clinic;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RentRequest.Status status;

    @OneToMany(mappedBy = "rentRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentRequestDayEntity> requestedDays;

    @OneToMany(mappedBy = "rentRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;
}
