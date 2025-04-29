package com.medspace.application.usecase.payment;

import com.medspace.application.service.PaymentService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeletePaymentByIdUseCase {
    @Inject
    PaymentService paymentService;

    public void execute(Long paymentId, User user) {
        if (!paymentService.validatePaymentOwnership(paymentId, user.getId())) {
            throw new SecurityException("User not authorized to delete this payment");
        }
        paymentService.deletePaymentById(paymentId);
    }
} 