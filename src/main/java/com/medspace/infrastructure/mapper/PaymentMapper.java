package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Payment;
import com.medspace.infrastructure.entity.PaymentEntity;

public class PaymentMapper {
    public static Payment toDomain(PaymentEntity entity) {
        if (entity == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(entity.getId());
        payment.setRentAgreementId(entity.getRentAgreementId());
        payment.setAmount(entity.getAmount());
        payment.setPaymentStatus(entity.getPaymentStatus());
        payment.setPaymentMethod(entity.getPaymentMethod());
        payment.setPaymentDate(entity.getPaymentDate());
        payment.setStripePaymentId(entity.getStripePaymentId());
        payment.setStripeCustomerId(entity.getStripeCustomerId());
        payment.setStripeInvoiceId(entity.getStripeInvoiceId());
        payment.setCreatedAt(entity.getCreatedAt());
        return payment;
    }

    public static PaymentEntity toEntity(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setRentAgreementId(payment.getRentAgreementId());
        entity.setAmount(payment.getAmount());
        entity.setPaymentStatus(payment.getPaymentStatus());
        entity.setPaymentMethod(payment.getPaymentMethod());
        entity.setPaymentDate(payment.getPaymentDate());
        entity.setStripePaymentId(payment.getStripePaymentId());
        entity.setStripeCustomerId(payment.getStripeCustomerId());
        entity.setStripeInvoiceId(payment.getStripeInvoiceId());
        entity.setCreatedAt(payment.getCreatedAt());
        return entity;
    }
} 