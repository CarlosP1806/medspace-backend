package com.medspace.application.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.Payment;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.PaymentRepository;
import com.medspace.domain.repository.RentRequestDayRepository;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.domain.repository.UserRepository;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestSpecialistsDashboardDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

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
    @Inject
    UserRepository userRepository;

    // Rent request methods

    public RentRequest createRentRequest(RentRequest rentRequest, Long tenantId, Long clinicId) {
        rentRequest.setCreatedAt(Instant.now());
        return rentRequestRepository.insert(rentRequest, tenantId, clinicId);
    }

    public void deleteRentRequestById(Long id) {
        rentRequestRepository.deleteById(id);
    }

    public RentRequest getRentRequestById(Long id) {
        return rentRequestRepository.findRequestById(id);
    }

    public List<RentRequest> listRentRequestsByUserId(RentRequestQueryFilterDTO queryDTO) {
        User.UserType userType = userRepository.getUserById(queryDTO.getUserId()).getUserType();
        if (userType == User.UserType.LANDLORD) {
            return rentRequestRepository.findByLandlordId(queryDTO);
        } else if (userType == User.UserType.TENANT) {
            return rentRequestRepository.findByTenantId(queryDTO);
        }

        return new ArrayList<>();
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

    public void updateRentRequestStatus(Long rentRequestId, RentRequest.Status status) {
        rentRequestRepository.updateRentRequestStatus(rentRequestId, status);
    }

    public Boolean validateRentRequestOwnership(Long rentRequestId, Long userId) {
        if (rentRequestId == null || userId == null) {
            return false;
        }
        RentRequest rentRequest = rentRequestRepository.findRequestById(rentRequestId);
        if (rentRequest == null || rentRequest.getTenant() == null) {
            return false;
        }
        return rentRequest.getClinic().getLandlord().getId().equals(userId);
    }

    public Boolean validateRentRequestSentByTenant(Long rentRequestId, Long tenantId) {
        if (rentRequestId == null || tenantId == null) {
            return false;
        }
        RentRequest rentRequest = rentRequestRepository.findRequestById(rentRequestId);
        if (rentRequest == null || rentRequest.getTenant() == null) {
            return false;
        }
        return rentRequest.getTenant().getId().equals(tenantId);
    }

    public Boolean isRentRequestPending(Long rentRequestId) {
        RentRequest rentRequest = rentRequestRepository.findRequestById(rentRequestId);
        return rentRequest.getStatus() == RentRequest.Status.PENDING;
    }

    public Boolean processRentRequestPayment(Long rentRequestId) {
        Boolean hasPendingStatus = isRentRequestPending(rentRequestId);
        if (!hasPendingStatus) {
            throw new IllegalArgumentException("Rent request is already processed");
        }
        return true;
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

    public Review assignReviewToRentRequest(Long reviewId, Long rentRequestId) {
        return reviewRepository.assignReviewToRentRequest(reviewId, rentRequestId);
    }

    public User getReviewAuthor(Long reviewId) {
        Review review = reviewRepository.getReviewById(reviewId);
        if (review == null) {
            return null;
        }

        if (review.getType() == Review.Type.CLINIC) {
            return review.getRentRequest().getTenant();
        } else if (review.getType() == Review.Type.LANDLORD) {
            return review.getRentRequest().getTenant();
        } else if (review.getType() == Review.Type.TENANT) {
            return review.getRentRequest().getClinic().getLandlord();
        } else {
            throw new IllegalArgumentException("Unsupported review type: " + review.getType());
        }
    }

    public Boolean validateReviewOwnership(Long reviewId, Long authorId) {
        if (reviewId == null || authorId == null) {
            return false;
        }

        Review review = reviewRepository.getReviewById(reviewId);
        User author = getReviewAuthor(reviewId);
        if (author == null || review == null) {
            return false;
        }

        return author.getId().equals(authorId);
    }

    // Returns all the reviews received by a user
    public List<Review> getReviewsByUserId(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        if (user.getUserType() == User.UserType.LANDLORD) {
            return reviewRepository.getReviewsByLandlordId(userId);
        } else if (user.getUserType() == User.UserType.TENANT) {
            return reviewRepository.getReviewsByTenantId(userId);
        }
        return new ArrayList<>();
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

    public List<GetRentRequestSpecialistsDashboardDTO> getSpecialistsDashboardData() {
        return rentRequestRepository.getSpecialistsDashboardData();
    }
}
