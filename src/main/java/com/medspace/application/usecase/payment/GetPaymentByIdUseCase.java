package com.medspace.application.usecase.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetPaymentByIdUseCase {
    @Inject
    RentService rentService;

    public Payment execute(Long paymentId, User user) {
        if (!rentService.validatePaymentOwnership(paymentId, user.getId())) {
            throw new SecurityException("User not authorized to view this payment");
        }
        return rentService.getPaymentById(paymentId);
    }
}
