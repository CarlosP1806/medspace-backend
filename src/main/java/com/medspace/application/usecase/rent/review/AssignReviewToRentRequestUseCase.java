package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignReviewToRentRequestUseCase {
    @Inject
    RentService rentService;

    public Review execute(Long reviewId, Long rentRequestId) {
        return rentService.assignReviewToRentRequest(reviewId, rentRequestId);
    }
}
