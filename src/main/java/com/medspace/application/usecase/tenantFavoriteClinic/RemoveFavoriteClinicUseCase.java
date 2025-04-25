package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.TenantFavoriteClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveFavoriteClinicUseCase {
    @Inject
    TenantFavoriteClinicService tenantFavoriteClinicService;

    public void execute(Long tenantId, Long clinicId) {
        tenantFavoriteClinicService.removeFavoriteClinic(tenantId, clinicId);
    }
}
