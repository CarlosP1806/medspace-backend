package com.medspace.application.usecase.review;
import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class AssignReviewToAuthorUseCase {
    @Inject
    ReviewService reviewService;

    public Review execute(Long reviewId, Long authorId) {
        return reviewService.assignAuthorToReview(reviewId, authorId);
    }
}

