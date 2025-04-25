package com.medspace.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tenant_favorite_clinics", 
       uniqueConstraints = @UniqueConstraint(
           name = "uk_tenant_clinic",
           columnNames = {"tenant_id", "clinic_id"}
       ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantFavoriteClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clinic_id", foreignKey = @ForeignKey(name = "fk_tenant_favorite_clinics_clinic"))
    private ClinicEntity clinic;

    @ManyToOne
    @JoinColumn(name = "tenant_id", foreignKey = @ForeignKey(name = "fk_tenant_favorite_clinics_tenant"))
    private UserEntity tenant;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}