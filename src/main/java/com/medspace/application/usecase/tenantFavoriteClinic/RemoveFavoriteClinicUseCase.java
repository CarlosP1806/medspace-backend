package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveFavoriteClinicUseCase {
    @Inject
    UserService userService;

    public void execute(Long tenantId, Long clinicId) {
        userService.deleteTenantFavoriteClinic(tenantId, clinicId);
    }
}
