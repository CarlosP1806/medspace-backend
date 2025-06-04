package com.medspace.infrastructure.dto.clinic;

import java.sql.Date;
import java.util.List;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.Clinic.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClinicDTO {
    private Long id;

    private String displayName;
    private Category category;
    private String description;

    private double pricePerDay;
    private int maxStayDays;
    private Date availableFromDate;
    private Date availableToDate;

    private String addressStreet;
    private String addressCity;
    private String addressState;
    private String addressZip;
    private String addressCountry;
    private String addressLongitude;
    private String addressLatitude;

    private int size; // size of the clinic, in square meters

    private Long landLordId;

    private Double averageRating; // average rating of Clinic, or null if there are no ratings

    private List<GetClinicPhotoDTO> photos;
    private List<GetClinicEquipmentDTO> equipments;
    private List<GetClinicAvailabilityDTO> availabilities;

    public GetClinicDTO(Clinic clinic, Double averageRating, List<GetClinicPhotoDTO> photos,
            List<GetClinicEquipmentDTO> equipments, List<GetClinicAvailabilityDTO> availabilities) {
        this.id = clinic.getId();
        this.displayName = clinic.getDisplayName();
        this.category = clinic.getCategory();
        this.description = clinic.getDescription();
        this.pricePerDay = clinic.getPricePerDay();
        this.maxStayDays = clinic.getMaxStayDays();
        this.availableFromDate = clinic.getAvailableFromDate();
        this.availableToDate = clinic.getAvailableToDate();
        this.addressStreet = clinic.getAddressStreet();
        this.addressCity = clinic.getAddressCity();
        this.addressState = clinic.getAddressState();
        this.addressZip = clinic.getAddressZip();
        this.addressCountry = clinic.getAddressCountry();
        this.addressLongitude = clinic.getAddressLongitude();
        this.addressLatitude = clinic.getAddressLatitude();
        this.averageRating = averageRating;

        this.size = clinic.getSize();

        this.landLordId = clinic.getLandlord().getId();

        this.photos = photos;
        this.equipments = equipments;
        this.availabilities = availabilities;
    }
}
