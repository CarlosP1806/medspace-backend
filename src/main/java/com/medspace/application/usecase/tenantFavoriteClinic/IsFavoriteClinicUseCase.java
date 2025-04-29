package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.TenantFavoriteClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IsFavoriteClinicUseCase {
    @Inject
    TenantFavoriteClinicService tenantFavoriteClinicService;

    public boolean execute(Long tenantId, Long clinicId) {
        return tenantFavoriteClinicService.isFavoriteClinic(tenantId, clinicId);
    }
}
