package com.medspace.application.usecase.rent.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdatePaymentStatusUseCase {
    @Inject
    RentService rentService;

    public Payment execute(Long paymentId, Payment.PaymentStatus newStatus, User user) {
        // Stubbed auth check
        if (!rentService.validatePaymentOwnership(paymentId, user.getId())) {
            throw new SecurityException("User not authorized to update this payment");
        }
        Payment payment = rentService.getPaymentById(paymentId);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setPaymentStatus(newStatus);
        return rentService.updatePayment(payment);
    }
}
