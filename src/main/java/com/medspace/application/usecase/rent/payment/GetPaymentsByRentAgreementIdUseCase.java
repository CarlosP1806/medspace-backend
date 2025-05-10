package com.medspace.application.usecase.rent.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetPaymentsByRentAgreementIdUseCase {
    @Inject
    RentService rentService;

    public List<Payment> execute(Long rentAgreementId, User user) {
        if (!rentService.validatePaymentOwnership(null, user.getId())) {
            throw new SecurityException("User not authorized to view these payments");
        }
        return rentService.getPaymentsByRentAgreementId(rentAgreementId);
    }
}
