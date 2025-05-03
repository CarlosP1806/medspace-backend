package com.medspace.infrastructure.dto;

import com.medspace.domain.model.TenantFavoriteClinic;
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
    private Long clinicId;

    public TenantFavoriteClinic toDomainModel() {
        TenantFavoriteClinic favoriteClinic = new TenantFavoriteClinic();
        return favoriteClinic;
    }
}
