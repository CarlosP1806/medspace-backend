package com.medspace.application.usecase.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class AssignReviewToAuthorUseCase {
    @Inject
    RentService rentService;

    public Review execute(Long reviewId, Long authorId) {
        return rentService.assignAuthorToReview(reviewId, authorId);
    }
}

