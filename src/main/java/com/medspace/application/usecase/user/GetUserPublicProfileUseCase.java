package com.medspace.application.usecase.user;

import java.util.ArrayList;
import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.application.service.RentService;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.clinic.GetClinicPreviewDTO;
import com.medspace.infrastructure.dto.review.GetReviewDTO;
import com.medspace.infrastructure.dto.user.GetUserPublicProfileDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetUserPublicProfileUseCase {
    @Inject
    UserService userService;
    @Inject
    ClinicService clinicService;
    @Inject
    RentService rentService;

    public GetUserPublicProfileDTO execute(Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        List<Review> reviews = rentService.getReviewsByUserId(userId);
        List<GetReviewDTO> reviewsDTO = new ArrayList<>();

        Double totalUserRating = 0.0;
        for (Review review : reviews) {
            User reviewAuthor = rentService.getReviewAuthor(review.getId());
            reviewsDTO.add(new GetReviewDTO(review, reviewAuthor));
            totalUserRating += review.getRating();
        }
        Double averageUserRating = reviews.isEmpty() ? null : totalUserRating / reviews.size();

        List<GetClinicPreviewDTO> ownedClinicsDTO = null;
        if (user.getUserType() == User.UserType.LANDLORD) {
            ownedClinicsDTO = new ArrayList<>();
            List<Clinic> ownedClinics = clinicService.getClinicsByLandlordId(userId);
            for (Clinic clinic : ownedClinics) {
                Double clinicRating = clinicService.getAverageRatingById(clinic.getId());
                String mainPhotoPath = clinicService.getMainPhotoPathByClinicId(clinic.getId());
                ownedClinicsDTO.add(new GetClinicPreviewDTO(clinic, clinicRating, mainPhotoPath));
            }

        }

        return new GetUserPublicProfileDTO(user, averageUserRating, reviewsDTO, ownedClinicsDTO);
    }
}
