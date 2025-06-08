package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Payment;
import com.medspace.domain.repository.PaymentRepository;
import com.medspace.infrastructure.entity.PaymentEntity;
import com.medspace.infrastructure.mapper.PaymentMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PaymentRepositoryImpl
        implements PaymentRepository, PanacheRepositoryBase<PaymentEntity, Long> {

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {
        PaymentEntity entity = PaymentMapper.toEntity(payment);
        persist(entity);
        return PaymentMapper.toDomain(entity);
    }

    @Override
    public Payment findPaymentById(Long paymentId) {
        PaymentEntity entity = findById(paymentId);
        if (entity == null) {
            return null;
        }
        return PaymentMapper.toDomain(entity);
    }

    @Override
    public List<Payment> findPaymentsByRentAgreementId(Long rentAgreementId) {
        List<PaymentEntity> entities = find("rentAgreementId", rentAgreementId).list();
        List<Payment> payments = new ArrayList<>();
        for (PaymentEntity entity : entities) {
            payments.add(PaymentMapper.toDomain(entity));
        }
        return payments;
    }

    @Override
    public List<Payment> findAllPayments() {
        List<PaymentEntity> entities = listAll();
        List<Payment> payments = new ArrayList<>();
        for (PaymentEntity entity : entities) {
            payments.add(PaymentMapper.toDomain(entity));
        }
        return payments;
    }

    @Override
    @Transactional
    public void deletePaymentById(Long paymentId) {
        PaymentEntity entity = findById(paymentId);
        if (entity != null) {
            delete(entity);
        } else {
            throw new NotFoundException("Payment with id " + paymentId + " not found");
        }
    }
}
