package com.medspace.infrastructure.dto;

import java.sql.Date;
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

    @NotBlank
    private String description;

    @NotNull
    private Double pricePerDay;

    @NotNull
    private Integer maxStayDays;

    @NotNull
    private Date availableFromDate;

    @NotNull
    private Date availableToDate;

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

    public Clinic toClinic() {
        Clinic clinic = new Clinic();

        clinic.setDisplayName(displayName);
        clinic.setCategory(Clinic.Category.valueOf(category));
        clinic.setDescription(description);
        clinic.setPricePerDay(pricePerDay);
        clinic.setMaxStayDays(maxStayDays);
        clinic.setAvailableFromDate(availableFromDate);
        clinic.setAvailableToDate(availableToDate);
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
