package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteReviewByIdUseCase {
    @Inject
    RentService rentService;

    @Transactional
    public void execute(Long reviewId, Long currentUserId) {
        Review review = rentService.getReviewById(reviewId);
        if (review == null || review.getAuthor() == null) {
            throw new ForbiddenException("Review not found or not linked to a user");
        }

        Long authorId = review.getAuthor().getId();
        Boolean isOwner = rentService.validateReviewOwnership(reviewId, currentUserId);
        if (!isOwner || !currentUserId.equals(authorId)) {
            throw new ForbiddenException("Delete unauthorized");
        }

        rentService.deleteReviewById(reviewId);
    }
}
