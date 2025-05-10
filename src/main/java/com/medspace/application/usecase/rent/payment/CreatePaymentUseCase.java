package com.medspace.application.usecase.rent.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreatePaymentUseCase {
    @Inject
    RentService rentService;

    public Payment execute(Payment payment, User user) {
        return rentService.createPayment(payment);
    }
}
