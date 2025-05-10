package com.medspace.application.usecase.user.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetFavoriteClinicByIdUseCase {
    @Inject
    UserService userService;

    public TenantFavoriteClinic execute(Long id) {
        return userService.getTenantFavoriteClinicById(id);
    }
}
