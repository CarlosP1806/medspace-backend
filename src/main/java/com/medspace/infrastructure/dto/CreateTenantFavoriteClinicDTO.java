package com.medspace.infrastructure.dto;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.domain.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTenantFavoriteClinicDTO {

    @NotNull
    private Long tenantId;

    @NotNull
    private Long clinicId;

    public TenantFavoriteClinic toDomainModel() {
        TenantFavoriteClinic favoriteClinic = new TenantFavoriteClinic();
        return favoriteClinic;
    }
}
