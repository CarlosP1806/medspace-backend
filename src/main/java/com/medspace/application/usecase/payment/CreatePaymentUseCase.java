package com.medspace.application.usecase.payment;

import com.medspace.application.service.PaymentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreatePaymentUseCase {
    @Inject
    PaymentService paymentService;

    public Payment execute(Payment payment, User user) {
        // TODO: Implement ownership/auth check after payment creation when RentAgreement is available
        return paymentService.createPayment(payment);
    }
} 