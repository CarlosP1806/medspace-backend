package com.medspace.application.usecase.user.tenantSpecialties;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantSpecialty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class GetTenantSpecialtyByIdUseCase {
    @Inject
    UserService userService;

    public TenantSpecialty execute(Long id) {
        TenantSpecialty tenantSpecialty = userService.getTenantSpecialtyById(id);

        return tenantSpecialty;
    }
}
