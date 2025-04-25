package com.medspace.infrastructure.dto;

import com.medspace.domain.model.TenantFavoriteClinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantFavoriteClinicResponseDTO {
    private Long id;
    private Long tenantId;
    private Long clinicId;
    private Instant createdAt;

    public static TenantFavoriteClinicResponseDTO fromFavorite(TenantFavoriteClinic favorite) {
        return new TenantFavoriteClinicResponseDTO(
            favorite.getId(),
            favorite.getTenant() != null ? favorite.getTenant().getId() : null,
            favorite.getClinic() != null ? favorite.getClinic().getId() : null,
            favorite.getCreatedAt()
        );
    }
}
