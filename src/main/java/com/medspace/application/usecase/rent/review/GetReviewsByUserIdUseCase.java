package com.medspace.application.usecase.rent.review;


import java.util.ArrayList;
import java.util.List;
import com.medspace.application.service.RentService;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.review.GetReviewDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class GetReviewsByUserIdUseCase {

    @Inject
    RentService rentService;

    @Inject
    UserService userService;

    public List<GetReviewDTO> execute(Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        List<Review> reviews = rentService.getReviewsByUserId(userId);

        List<GetReviewDTO> reviewsDTO = new ArrayList<>();

        for (Review review : reviews) {
            User reviewAuthor = rentService.getReviewAuthor(review.getId());
            reviewsDTO.add(new GetReviewDTO(review, reviewAuthor));
        }

        // Fetch reviews by user ID from the database
        return reviewsDTO;
    }

}
