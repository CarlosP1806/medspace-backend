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
public class UpdateClinicDTO {
    private String displayName;

    private String category;

    private String description;

    private Double pricePerDay;

    private Integer maxStayDays;


    public Clinic toClinic() {
        Clinic clinic = new Clinic();


        if (displayName != null && !displayName.isBlank()) {
            clinic.setDisplayName(displayName);
        }

        if (category != null && !category.isBlank()) {
            try {
                clinic.setCategory(Clinic.Category.valueOf(category.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid category: " + category);
            }
        }

        if (description != null && !description.isBlank()) {
            clinic.setDescription(description);
        }

        if (pricePerDay != null && pricePerDay > 0) {
            clinic.setPricePerDay(pricePerDay);
        }

        if (maxStayDays != null && maxStayDays > 0) {
            clinic.setMaxStayDays(maxStayDays);
        }

        return clinic;
    }
}
