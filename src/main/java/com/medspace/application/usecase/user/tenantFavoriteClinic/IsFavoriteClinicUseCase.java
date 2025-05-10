package com.medspace.application.usecase.user.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IsFavoriteClinicUseCase {
    @Inject
    UserService userService;

    public boolean execute(Long tenantId, Long clinicId) {
        return userService.isTenantFavoriteClinic(tenantId, clinicId);
    }
}
