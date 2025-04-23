package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class DeleteReviewByIdUseCase {
    @Inject
    ReviewService reviewService;

    public void execute(Long id) {
        reviewService.deleteReviewById(id);
    }
}
