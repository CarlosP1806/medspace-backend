package com.medspace.application.usecase.review;

import com.medspace.application.service.ReviewService;
import com.medspace.domain.model.Review;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteReviewByIdUseCase {

    @Inject
    ReviewService reviewService;

    @Transactional
    public void execute(Long reviewId, Long currentUserId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review == null || review.getAuthor() == null) {
            throw new ForbiddenException("Review not found or not linked to a user");
        }

        Long authorId = review.getAuthor().getId();
        Boolean isOwner = reviewService.validateReviewOwnership(reviewId, currentUserId);
        if (!isOwner || !currentUserId.equals(authorId)) {
            throw new ForbiddenException("Delete unauthorized");
        }

        reviewService.deleteReviewById(reviewId);
    }
}
