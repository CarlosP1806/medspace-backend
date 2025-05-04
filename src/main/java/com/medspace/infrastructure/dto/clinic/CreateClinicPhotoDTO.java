package com.medspace.infrastructure.dto.clinic;

import com.medspace.domain.model.ClinicPhoto;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClinicPhotoDTO {
    @FormParam("clinicId")
    @PartType(MediaType.TEXT_PLAIN)
    @NotNull
    private Long clinicId;

    @FormParam("isPrimary")
    @PartType(MediaType.TEXT_PLAIN)
    @NotNull
    private Boolean isPrimary;

    @FormParam("photo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream photo;

    public ClinicPhoto toClinicPhoto() {
        ClinicPhoto clinicPhoto = new ClinicPhoto();
        clinicPhoto.setIsPrimary(isPrimary);
        return clinicPhoto;
    }
}
