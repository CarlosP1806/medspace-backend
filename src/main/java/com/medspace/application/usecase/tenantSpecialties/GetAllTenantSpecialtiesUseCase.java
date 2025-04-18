package com.medspace.application.usecase.tenantSpecialties;

import java.util.List;
import com.medspace.application.service.TenantSpecialtyService;
import com.medspace.domain.model.TenantSpecialty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetAllTenantSpecialtiesUseCase {

    @Inject
    TenantSpecialtyService tenantSpecialtyService;


    public List<TenantSpecialty> execute() {
        return tenantSpecialtyService.getAllTenantSpecialties();
    }

}
