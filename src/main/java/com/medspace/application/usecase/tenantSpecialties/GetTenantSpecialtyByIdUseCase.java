package com.medspace.application.usecase.tenantSpecialties;

import com.medspace.application.service.TenantSpecialtyService;
import com.medspace.domain.model.TenantSpecialty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class GetTenantSpecialtyByIdUseCase {

    @Inject
    TenantSpecialtyService tenantSpecialtyService;

    public TenantSpecialty execute(Long id) {
        TenantSpecialty tenantSpecialty = tenantSpecialtyService.getTenantSpecialtyById(id);

        return tenantSpecialty;
    }

}
