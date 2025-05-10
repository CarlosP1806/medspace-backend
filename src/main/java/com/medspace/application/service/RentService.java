package com.medspace.application.service;

import java.time.Instant;
import java.util.List;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.model.Review;
import com.medspace.domain.repository.PaymentRepository;
import com.medspace.domain.repository.RentRequestDayRepository;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentService {
    @Inject
    RentRequestRepository rentRequestRepository;
    @Inject
    RentRequestDayRepository rentRequestDayRepository;
    @Inject
    ReviewRepository reviewRepository;
    @Inject
    PaymentRepository paymentRepository;

    // Rent request methods

    public RentRequest createRentRequest(RentRequest rentRequest, Long tenantId, Long clinicId) {
        rentRequest.setCreatedAt(Instant.now());
        return rentRequestRepository.insert(rentRequest, tenantId, clinicId);
    }

    public void deleteRentRequestById(Long id) {
        rentRequestRepository.deleteById(id);
    }

    public List<RentRequest> listRentRequestsByLandlordId(RentRequestQueryFilterDTO queryDTO) {
        return rentRequestRepository.findByLandlordId(queryDTO);
    }

    public List<RentRequest> listAllRentRequests() {
        return rentRequestRepository.findAllRequests();
    }

    public RentRequestDay createRentRequestDay(RentRequestDay rentRequestDay, Long rentRequestId) {
        rentRequestDay.setCreatedAt(Instant.now());
        rentRequestDay =
                rentRequestDayRepository.insertRentRequestDay(rentRequestDay, rentRequestId);
        return rentRequestDay;
    }

    public List<RentRequestDay> listRentRequestDaysByRentRequestId(Long rentRequestId) {
        return rentRequestDayRepository.getRentRequestDaysByRentRequestId(rentRequestId);
    }

    // Review methods

    public Review createReview(Review review) {
        review.setCreatedAt(Instant.now());
        review = reviewRepository.insertReview(review);
        return review;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getReviewById(id);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteReviewById(id);
    }

    public Review assignAuthorToReview(Long reviewId, Long authorId) {
        return reviewRepository.assignReviewToAuthor(reviewId, authorId);
    }

    public Review assignReviewToClinic(Long reviewId, Long clinicId) {
        return reviewRepository.assignReviewToClinic(reviewId, clinicId);
    }

    public Review assignReviewToRentAgreement(Long reviewId, Long rentAgreementId) {
        return reviewRepository.assignReviewToRentAgreement(reviewId, rentAgreementId);
    }

    public Boolean validateReviewOwnership(Long reviewId, Long authorId) {
        if (reviewId == null || authorId == null) {
            return false;
        }
        Review review = reviewRepository.getReviewById(reviewId);
        if (review == null || review.getAuthor() == null) {
            return false;
        }
        // compara con authorId, no con reviewId
        return review.getAuthor().getId().equals(authorId);
    }

    public List<Review> getReviewsByClinicId(Long clinicId) {
        return reviewRepository.getReviewsByClinicId(clinicId);
    }

    // Payment methods

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
        // For now, always return true to allow all actions
        return true;
    }
}
