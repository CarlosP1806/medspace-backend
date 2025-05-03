package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class CreateReviewUseCase {
    @Inject
    ReviewService reviewService;

    public Review execute(Review review) {
        return reviewService.createReview(review);
    }
}
