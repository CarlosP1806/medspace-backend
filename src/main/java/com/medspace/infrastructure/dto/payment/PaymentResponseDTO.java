package com.medspace.infrastructure.dto.payment;

import com.medspace.domain.model.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private Long rentAgreementId;
    private Double amount;
    private Payment.PaymentStatus paymentStatus;
    private Payment.PaymentMethod paymentMethod;
    private Instant paymentDate;
    private String stripePaymentId;
    private String stripeCustomerId;
    private String stripeInvoiceId;
    private Instant createdAt;

    public PaymentResponseDTO(Payment payment) {
        if (payment != null) {
            this.id = payment.getId();
            this.rentAgreementId = payment.getRentAgreementId();
            this.amount = payment.getAmount();
            this.paymentStatus = payment.getPaymentStatus();
            this.paymentMethod = payment.getPaymentMethod();
            this.paymentDate = payment.getPaymentDate();
            this.stripePaymentId = payment.getStripePaymentId();
            this.stripeCustomerId = payment.getStripeCustomerId();
            this.stripeInvoiceId = payment.getStripeInvoiceId();
            this.createdAt = payment.getCreatedAt();
        }
    }
}
