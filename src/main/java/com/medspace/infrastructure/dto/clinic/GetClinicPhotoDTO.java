package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.ClinicPhoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetClinicPhotoDTO {
    private Long id;
    private Long clinicId;
    private String path;
    private Boolean isPrimary;

    public GetClinicPhotoDTO(ClinicPhoto clinicPhoto) {
        this.id = clinicPhoto.getId();
        this.clinicId = clinicPhoto.getClinic().getId();
        this.path = clinicPhoto.getPath();
        this.isPrimary = clinicPhoto.getIsPrimary();
    }
}
