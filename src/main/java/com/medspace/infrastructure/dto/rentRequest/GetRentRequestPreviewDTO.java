package com.medspace.infrastructure.dto.rentRequest;

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
    private String tenantFullName;
    private String tenantProfilePictureUrl;

    public GetRentRequestPreviewDTO(RentRequest rentRequest, Clinic clinic, User tenant) {
        this.id = rentRequest.getId();
        this.comments = rentRequest.getComments();
        this.status = rentRequest.getStatus().toString();

        this.tenantId = tenant.getId();
        this.clinicId = clinic.getId();

        this.clinicDisplayName = clinic.getDisplayName();
        this.tenantFullName = tenant.getFullName();
        this.tenantProfilePictureUrl = tenant.getProfilePictureUrl();
    }
}
