package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.TenantFavoriteClinicService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetFavoriteClinicsByTenantIdUseCase {
    @Inject
    TenantFavoriteClinicService tenantFavoriteClinicService;

    public List<TenantFavoriteClinic> execute(Long tenantId) {
        return tenantFavoriteClinicService.getFavoriteClinicsByTenantId(tenantId);
    }
}
