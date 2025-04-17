package com.medspace.domain.repository;

import java.util.List;
import com.medspace.domain.model.TenantSpeciality;

public interface TenantSpecialityRepository {

    public List<TenantSpeciality> getAllTenantSpecialities();

    public TenantSpeciality getTenantSpecialityById(Long id);
}
