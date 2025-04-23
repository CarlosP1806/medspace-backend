package com.medspace.application.usecase.review;


import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetReviewByIdUseCase {
    @Inject
    ReviewService reviewService;

    public Review execute(Long id) {
        return reviewService.getReviewById(id);
    }
}
