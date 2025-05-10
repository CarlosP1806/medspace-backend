package com.medspace.application.usecase.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeletePaymentByIdUseCase {
    @Inject
    RentService rentService;

    public void execute(Long paymentId, User user) {
        if (!rentService.validatePaymentOwnership(paymentId, user.getId())) {
            throw new SecurityException("User not authorized to delete this payment");
        }
        rentService.deletePaymentById(paymentId);
    }
}
