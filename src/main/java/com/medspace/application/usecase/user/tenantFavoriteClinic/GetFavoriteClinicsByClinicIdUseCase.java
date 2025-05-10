package com.medspace.application.usecase.user.tenantFavoriteClinic;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.TenantFavoriteClinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetFavoriteClinicsByClinicIdUseCase {
    @Inject
    UserService userService;

    public List<TenantFavoriteClinic> execute(Long clinicId) {
        return userService.getFavoriteClinicsByClinicId(clinicId);
    }
}
