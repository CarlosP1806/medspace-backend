package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateReviewUseCase {
    @Inject
    RentService rentService;

    public Review execute(Review review) {
        return rentService.createReview(review);
    }
}
