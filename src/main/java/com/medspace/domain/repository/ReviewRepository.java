package com.medspace.domain.repository;

import com.medspace.domain.model.Review;
import java.util.List;

public interface ReviewRepository {
    public Review insertReview(Review review);

    public List<Review> getAllReviews();

    public Review getReviewById(Long id);

    public List<Review> getReviewsByClinicId(Long clinicId);

    public Void deleteReviewById(Long id);

    public Review assignReviewToRentAgreement(Long reviewId, Long rentAgreementId);

    public Review assignReviewToClinic(Long reviewId, Long clinicId);

    public Review assignReviewToAuthor(Long reviewId, Long authorId);
}
