package com.medspace.application.usecase.rent.review;


import com.medspace.application.service.RentService;
import com.medspace.domain.model.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetReviewByIdUseCase {
    @Inject
    RentService rentService;

    public Review execute(Long id) {
        return rentService.getReviewById(id);
    }
}
