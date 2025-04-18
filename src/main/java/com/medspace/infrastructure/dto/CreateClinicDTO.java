package com.medspace.infrastructure.dto;

import com.medspace.domain.model.Clinic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClinicDTO {
    @NotBlank
    private String displayName;

    @NotBlank
    private String category;

    @NotNull
    private Double pricePerDay;

    @NotNull
    private Integer maxStayDays;

    @NotBlank
    private String addressStreet;

    @NotBlank
    private String addressCity;

    @NotBlank
    private String addressState;

    @NotBlank
    private String addressZip;

    @NotBlank
    private String addressCountry;

    @NotBlank
    private String addressLongitude;

    @NotBlank
    private String addressLatitude;

    @NotNull
    private Long userId;

    public Clinic toClinic() {
        Clinic clinic = new Clinic();

        clinic.setDisplayName(displayName);
        clinic.setCategory(Clinic.Category.valueOf(category));
        clinic.setPricePerDay(pricePerDay);
        clinic.setMaxStayDays(maxStayDays);
        clinic.setAddressStreet(addressStreet);
        clinic.setAddressCity(addressCity);
        clinic.setAddressState(addressState);
        clinic.setAddressZip(addressZip);
        clinic.setAddressCountry(addressCountry);
        clinic.setAddressLongitude(addressLongitude);
        clinic.setAddressLatitude(addressLatitude);

        return clinic;
    }
}
