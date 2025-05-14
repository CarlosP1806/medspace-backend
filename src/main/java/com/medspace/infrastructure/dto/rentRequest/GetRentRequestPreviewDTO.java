package com.medspace.infrastructure.dto.rentRequest;

import java.sql.Date;
import java.util.List;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRentRequestPreviewDTO {
    private Long id;
    private String comments;
    private String status;

    private Long tenantId;
    private Long clinicId;

    private String clinicDisplayName;
    private String clinicAddress;
    private String clinicMainPhotoPath;

    private String tenantFullName;
    private String tenantProfilePictureUrl;
    private String tenantSpecialty;

    private List<Date> requestedDays;

    public GetRentRequestPreviewDTO(RentRequest rentRequest, Clinic clinic, User tenant,
            List<Date> requestedDays, String clinicMainPhotoPath) {
        this.id = rentRequest.getId();
        this.comments = rentRequest.getComments();
        this.status = rentRequest.getStatus().toString();

        this.tenantId = tenant.getId();
        this.clinicId = clinic.getId();

        this.clinicDisplayName = clinic.getDisplayName();
        this.clinicAddress = clinic.getAddressStreet() + ", " + clinic.getAddressCity() + ", "
                + clinic.getAddressState() + ", " + clinic.getAddressCountry();
        this.clinicMainPhotoPath = clinicMainPhotoPath;

        this.tenantFullName = tenant.getFullName();
        this.tenantProfilePictureUrl = tenant.getPfpPath();
        this.tenantSpecialty = tenant.getTenantSpecialty().getName();

        this.requestedDays = requestedDays;
    }
}
