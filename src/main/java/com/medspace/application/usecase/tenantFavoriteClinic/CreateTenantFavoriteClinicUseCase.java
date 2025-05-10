package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateTenantFavoriteClinicUseCase {
    @Inject
    UserService userService;

    public TenantFavoriteClinic execute(TenantFavoriteClinic favoriteClinic) {
        return userService.createTenantFavoriteClinic(favoriteClinic);
    }
}
