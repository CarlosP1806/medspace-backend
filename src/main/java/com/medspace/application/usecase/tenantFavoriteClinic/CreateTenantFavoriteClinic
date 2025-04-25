package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.TenantFavoriteClinicService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateTenantFavoriteClinicUseCase {
    @Inject
    TenantFavoriteClinicService tenantFavoriteClinicService;

    public TenantFavoriteClinic execute(TenantFavoriteClinic favoriteClinic) {
        return tenantFavoriteClinicService.createTenantFavoriteClinic(favoriteClinic);
    }
}
