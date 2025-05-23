package com.medspace.application.usecase.rent.review;

import java.util.ArrayList;
import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.application.service.RentService;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.review.GetReviewDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetReviewsByClinicIdUseCase {

    @Inject
    RentService rentService;

    @Inject
    ClinicService clinicService;

    public List<GetReviewDTO> execute(Long clinicId) {

        Clinic clinic = clinicService.getClinicById(clinicId);
        if (clinic == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        List<Review> reviews = rentService.getReviewsByClinicId(clinicId);


        List<GetReviewDTO> reviewsDTO = new ArrayList<>();

        for (Review review : reviews) {
            User reviewAuthor = rentService.getReviewAuthor(review.getId());
            reviewsDTO.add(new GetReviewDTO(review, reviewAuthor));
        }

        return reviewsDTO;
    }
}
