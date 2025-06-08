package com.medspace.domain.repository;

import com.medspace.domain.model.Payment;
import java.util.List;

public interface PaymentRepository {
    public Payment savePayment(Payment payment);

    public Payment findPaymentById(Long paymentId);

    public List<Payment> findPaymentsByRentAgreementId(Long rentAgreementId);

    public List<Payment> findAllPayments();

    public void deletePaymentById(Long paymentId);
}
