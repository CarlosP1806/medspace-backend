package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.TenantFavoriteClinicService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetFavoriteClinicByIdUseCase {
    @Inject
    TenantFavoriteClinicService tenantFavoriteClinicService;

    public TenantFavoriteClinic execute(Long id) {
        return tenantFavoriteClinicService.getFavoriteClinicById(id);
    }
}
