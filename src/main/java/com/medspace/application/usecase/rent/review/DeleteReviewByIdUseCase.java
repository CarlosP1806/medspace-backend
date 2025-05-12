package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
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
        Boolean isOwner = rentService.validateReviewOwnership(reviewId, currentUserId);
        if (!isOwner) {
            throw new ForbiddenException("You are not allowed to delete this review");
        }
        rentService.deleteReviewById(reviewId);
    }
}
