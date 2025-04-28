package com.medspace.application.service;

import com.medspace.domain.model.Payment;
import com.medspace.domain.repository.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class PaymentService {
    @Inject
    PaymentRepository paymentRepository;

    // TODO: Inject RentAgreementRepository when available
    // @Inject
    // RentAgreementRepository rentAgreementRepository;

    public Payment createPayment(Payment payment) {
        payment.setCreatedAt(Instant.now());
        payment = paymentRepository.savePayment(payment);
        return payment;
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findPaymentById(paymentId);
    }

    public List<Payment> getPaymentsByRentAgreementId(Long rentAgreementId) {
        return paymentRepository.findPaymentsByRentAgreementId(rentAgreementId);
    }

    public void deletePaymentById(Long paymentId) {
        paymentRepository.deletePaymentById(paymentId);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.savePayment(payment);
    }

    public boolean validatePaymentOwnership(Long paymentId, Long userId) {
        // TODO: Implement real ownership check when RentAgreement is available
        // For now, always return true to allow all actions
        return true;
    }
} 