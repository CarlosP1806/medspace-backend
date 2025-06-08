package com.medspace.application.usecase.rent.payment;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetAllPaymentsUseCase {
    @Inject
    RentService rentService;

    public List<Payment> execute(User user) {
        return rentService.getAllPayments();
    }
}
