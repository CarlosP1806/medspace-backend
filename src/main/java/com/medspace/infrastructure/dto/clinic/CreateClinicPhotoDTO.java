package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.ClinicPhoto;
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
public class CreateClinicPhotoDTO {
    @NotNull
    private Long clinicId;

    @NotNull
    private Boolean isPrimary;

    @NotBlank
    private String path;

    public ClinicPhoto toClinicPhoto() {
        ClinicPhoto clinicPhoto = new ClinicPhoto();
        clinicPhoto.setIsPrimary(isPrimary);
        clinicPhoto.setPath(path);
        return clinicPhoto;
    }
}
