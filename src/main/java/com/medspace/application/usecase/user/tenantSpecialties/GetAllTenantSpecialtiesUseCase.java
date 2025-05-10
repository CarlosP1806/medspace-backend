package com.medspace.application.usecase.user.tenantSpecialties;

import java.util.List;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantSpecialty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetAllTenantSpecialtiesUseCase {
    @Inject
    UserService userService;

    public List<TenantSpecialty> execute() {
        return userService.getAllTenantSpecialties();
    }

}
