package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AssignReviewToClinicUseCase {
    @Inject
    RentService rentService;

    @Transactional
    public Review execute(Long reviewId, Long clinicId) {
        if (clinicId == null) {
            throw new IllegalArgumentException("Clinic ID cannot be null");
        }
        return rentService.assignReviewToClinic(reviewId, clinicId);
    }

}
