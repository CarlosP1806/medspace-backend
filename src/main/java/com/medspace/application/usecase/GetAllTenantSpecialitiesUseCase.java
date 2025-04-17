package com.medspace.application.usecase;

import java.util.List;
import com.medspace.application.service.TenantSpecialityService;
import com.medspace.domain.model.TenantSpeciality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetAllTenantSpecialitiesUseCase {

    @Inject
    TenantSpecialityService tenantSpecialityService;


    public List<TenantSpeciality> execute() {
        return tenantSpecialityService.getAllTenantSpecialities();
    }

}
