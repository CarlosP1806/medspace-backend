package com.medspace.domain.repository;

import java.util.List;
import com.medspace.domain.model.TenantSpecialty;

public interface TenantSpecialtyRepository {

    public List<TenantSpecialty> getAllTenantSpecialties();

    public TenantSpecialty getTenantSpecialtyById(Long id);
}
