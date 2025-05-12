package com.medspace.domain.repository;

import com.medspace.domain.model.Review;
import java.util.List;

public interface ReviewRepository {
    public Review insertReview(Review review);

    public List<Review> getAllReviews();

    public Review getReviewById(Long id);

    public List<Review> getReviewsByLandlordId(Long landlordId);

    public List<Review> getReviewsByTenantId(Long tenantId);

    public List<Review> getReviewsByClinicId(Long clinicId);

    public Void deleteReviewById(Long id);

    public Review assignReviewToRentRequest(Long reviewId, Long rentRequestId);
}
