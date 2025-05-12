package com.medspace.infrastructure.dto.user;

import java.time.Instant;
import java.util.List;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.clinic.GetClinicPreviewDTO;
import com.medspace.infrastructure.dto.review.GetReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPublicProfileDTO {
    private Long id;
    private String fullname;
    private Double averageRating; // average rating of User, or null if there are no ratings
    private TenantSpecialty tenantSpecialty;
    private User.UserType userType;
    private Instant createdAt;
    private String pfpPath;

    private List<GetClinicPreviewDTO> clinics; // clinics owned by the user, if landlord
    private List<GetReviewDTO> reviews; // reviews received by other users

    public GetUserPublicProfileDTO(User user, Double userAverageRating, List<GetReviewDTO> reviews,
            List<GetClinicPreviewDTO> ownedClinics) {
        this.id = user.getId();
        this.fullname = user.getFullName();
        this.averageRating = userAverageRating;
        this.tenantSpecialty = user.getTenantSpecialty();
        this.userType = user.getUserType();
        this.createdAt = user.getCreatedAt();
        this.pfpPath = user.getPfpPath();
        this.clinics = ownedClinics;
        this.reviews = reviews;
    }
}
