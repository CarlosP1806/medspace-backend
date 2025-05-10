package com.medspace.application.usecase.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetFavoriteClinicsByTenantIdUseCase {
    @Inject
    UserService userService;

    public List<TenantFavoriteClinic> execute(Long tenantId) {
        return userService.getFavoriteClinicsByTenantId(tenantId);
    }
}
