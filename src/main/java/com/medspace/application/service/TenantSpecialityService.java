package com.medspace.application.service;

import java.util.List;
import com.medspace.domain.model.TenantSpeciality;
import com.medspace.domain.repository.TenantSpecialityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TenantSpecialityService {

    @Inject
    TenantSpecialityRepository tenantSpecialityRepository;

    public TenantSpeciality getTenantSpecialityById(Long id) {
        return tenantSpecialityRepository.getTenantSpecialityById(id);
    }

    public List<TenantSpeciality> getAllTenantSpecialities() {
        return tenantSpecialityRepository.getAllTenantSpecialities();
    }

}
