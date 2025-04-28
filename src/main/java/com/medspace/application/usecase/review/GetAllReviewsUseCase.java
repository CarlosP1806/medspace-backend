package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllReviewsUseCase {
    @Inject
    ReviewService reviewService;

    public List<Review> execute() {
        return reviewService.getAllReviews();
    }
}
