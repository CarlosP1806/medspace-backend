package com.medspace.application.usecase.rent.review;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.Review;
import com.medspace.infrastructure.dto.review.CreateReviewDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CreateReviewUseCase {

    @Inject
    RentService rentService;

    public Review execute(CreateReviewDTO createReviewDTO, Review.Type type) {

        // validate rent request
        RentRequest rentRequest =
                rentService.getRentRequestById(createReviewDTO.getRentRequestId());

        if (rentRequest == null) {
            throw new NotFoundException("Rent request not found");
        }

        Review review = createReviewDTO.toReview(type);
        review.setRentRequest(rentRequest);

        // Save the review to the database
        return rentService.createReview(review);
    }

}
