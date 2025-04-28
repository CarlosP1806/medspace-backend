package com.medspace.application.usecase.payment;

import com.medspace.application.service.PaymentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetPaymentsByRentAgreementIdUseCase {
    @Inject
    PaymentService paymentService;

    public List<Payment> execute(Long rentAgreementId, User user) {
        // TODO: Implement proper ownership check when RentAgreement is available
        if (!paymentService.validatePaymentOwnership(null, user.getId())) {
            throw new SecurityException("User not authorized to view these payments");
        }
        return paymentService.getPaymentsByRentAgreementId(rentAgreementId);
    }
} 