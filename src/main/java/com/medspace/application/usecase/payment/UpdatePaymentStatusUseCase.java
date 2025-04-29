package com.medspace.application.usecase.payment;

import com.medspace.application.service.PaymentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdatePaymentStatusUseCase {
    @Inject
    PaymentService paymentService;

    public Payment execute(Long paymentId, Payment.PaymentStatus newStatus, User user) {
        // Stubbed auth check
        if (!paymentService.validatePaymentOwnership(paymentId, user.getId())) {
            throw new SecurityException("User not authorized to update this payment");
        }
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setPaymentStatus(newStatus);
        return paymentService.updatePayment(payment);
    }
} 