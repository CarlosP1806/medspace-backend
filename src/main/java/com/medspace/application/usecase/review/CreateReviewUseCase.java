package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateReviewUseCase {
    @Inject
    ReviewService reviewService;

    public Review execute(Review review) {
        return reviewService.createReview(review);
    }
}
