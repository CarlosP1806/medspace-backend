package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.Clinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClinicPreviewDTO {
    private Long id;
    private String displayName;
    private String addressState;
    private Double averageRating;
    private Clinic.Category category;
    private Double pricePerDay;
    private String description;
    private String mainPhotoPath;

    public GetClinicPreviewDTO(Clinic clinic, Double averageRating, String mainPhotoPath) {
        this.id = clinic.getId();
        this.displayName = clinic.getDisplayName();
        this.addressState = clinic.getAddressState();
        this.averageRating = averageRating;
        this.category = clinic.getCategory();
        this.pricePerDay = clinic.getPricePerDay();
        this.description = clinic.getDescription();
        this.mainPhotoPath = mainPhotoPath;
    }
}
