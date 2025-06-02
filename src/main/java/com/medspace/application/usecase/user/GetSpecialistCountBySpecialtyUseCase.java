package com.medspace.application.usecase.user;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantSpecialty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetSpecialistCountBySpecialtyUseCase {
    @Inject
    UserService userService;

    public long execute(TenantSpecialty specialty) {
        return userService.countTenantsBySpecialty(specialty);
    }
}
