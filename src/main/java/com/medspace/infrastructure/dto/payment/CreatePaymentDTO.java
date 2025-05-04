package com.medspace.infrastructure.dto.payment;

import com.medspace.domain.model.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDTO {
    private Long rentAgreementId;
    private Double amount;
    private Payment.PaymentMethod paymentMethod;
    private String stripePaymentId;
    private String stripeCustomerId;
    private String stripeInvoiceId;

    public Payment toPayment() {
        Payment payment = new Payment();
        payment.setRentAgreementId(rentAgreementId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setStripePaymentId(stripePaymentId);
        payment.setStripeCustomerId(stripeCustomerId);
        payment.setStripeInvoiceId(stripeInvoiceId);
        payment.setPaymentStatus(Payment.PaymentStatus.PENDING);
        return payment;
    }
}
