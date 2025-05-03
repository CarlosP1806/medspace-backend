package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AssignReviewToClinicUseCase {
    @Inject
    ReviewService reviewService;

    @Transactional
    public Review execute(Long reviewId, Long clinicId) {
        if (clinicId == null) {
            throw new IllegalArgumentException("Clinic ID cannot be null");
        }
        return reviewService.assignReviewToClinic(reviewId, clinicId);
    }

}
