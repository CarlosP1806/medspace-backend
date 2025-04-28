package com.medspace.infrastructure.entity;

import com.medspace.domain.model.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rent_agreement_id", nullable = false)
    private Long rentAgreementId;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private Payment.PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private Payment.PaymentMethod paymentMethod;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "stripe_payment_id")
    private String stripePaymentId;

    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    @Column(name = "stripe_invoice_id")
    private String stripeInvoiceId;

    @Column(name = "created_at")
    private Instant createdAt;
} 