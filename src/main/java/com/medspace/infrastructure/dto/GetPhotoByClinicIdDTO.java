package com.medspace.infrastructure.dto;

import com.medspace.domain.model.ClinicPhoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoByClinicIdDTO {
    private Long id;
    private Long clinicId;
    private String url;

    public GetPhotoByClinicIdDTO(ClinicPhoto clinicPhoto) {
        this.id = clinicPhoto.getId();
        this.clinicId = clinicPhoto.getClinic().getId();
        this.url = clinicPhoto.getUrl();
    }
}
