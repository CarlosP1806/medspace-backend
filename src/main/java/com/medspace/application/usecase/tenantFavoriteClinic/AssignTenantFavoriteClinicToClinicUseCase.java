package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AssignTenantFavoriteClinicToClinicUseCase {
    @Inject
    UserService userService;

    public TenantFavoriteClinic execute(Long favoriteClinicId, Long clinicId) {
        return userService.assignFavoriteClinicToClinic(favoriteClinicId, clinicId);
    }
}
