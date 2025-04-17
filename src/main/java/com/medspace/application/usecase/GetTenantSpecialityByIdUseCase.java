package com.medspace.application.usecase;

import com.medspace.application.service.TenantSpecialityService;
import com.medspace.domain.model.TenantSpeciality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class GetTenantSpecialityByIdUseCase {

    @Inject
    TenantSpecialityService tenantSpecialityService;

    public TenantSpeciality execute(Long id) {
        TenantSpeciality tenantSpeciality = tenantSpecialityService.getTenantSpecialityById(id);

        return tenantSpeciality;
    }

}
