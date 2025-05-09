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
public class MyClinicDTO {
    private Long id;

    private String displayName;
    private String addressState;
    private String mainPhotoPath;


    public MyClinicDTO(Clinic clinic, GetClinicPhotoDTO mainPhoto) {
        this.id = clinic.getId();
        this.displayName = clinic.getDisplayName();
        this.addressState = clinic.getAddressState();
        this.mainPhotoPath = mainPhoto.getPath();
    }
}
