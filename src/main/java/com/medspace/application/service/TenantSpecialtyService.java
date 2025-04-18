package com.medspace.application.service;

import java.util.List;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.repository.TenantSpecialtyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TenantSpecialtyService {

    @Inject
    TenantSpecialtyRepository tenantSpecialtyRepository;

    public TenantSpecialty getTenantSpecialtyById(Long id) {
        return tenantSpecialtyRepository.getTenantSpecialtyById(id);
    }

    public List<TenantSpecialty> getAllTenantSpecialties() {
        return tenantSpecialtyRepository.getAllTenantSpecialties();
    }

}
