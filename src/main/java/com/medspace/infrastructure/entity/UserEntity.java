package com.medspace.infrastructure.entity;

import java.sql.Timestamp;
import java.util.List;
import com.medspace.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "firebase_uid", unique = true)
    private String firebaseUid;

    @Column(name = "pfp_path")
    private String pfpPath;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "bio")
    private String bio;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private User.UserType userType;

    @ManyToOne
    @JoinColumn(name = "tenant_specialty_id")
    private TenantSpecialtyEntity tenantSpecialty;

    @Column(name = "tenant_license_path")
    private String tenantLicensePath;

    @Column(name = "average_rating")
    private Float averageRating;

    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    @Column(name = "default_payment_method")
    private String defaultPaymentMethod;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicEntity> clinics;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentRequestEntity> rentRequests;
}
