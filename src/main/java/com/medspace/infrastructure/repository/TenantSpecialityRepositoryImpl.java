package com.medspace.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.TenantSpeciality;
import com.medspace.domain.repository.TenantSpecialityRepository;
import com.medspace.infrastructure.entity.TenantSpecialityEntity;
import com.medspace.infrastructure.mapper.TenantSpecialityMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenantSpecialityRepositoryImpl
        implements TenantSpecialityRepository, PanacheRepositoryBase<TenantSpecialityEntity, Long> {

    @Override
    public List<TenantSpeciality> getAllTenantSpecialities() {
        List<TenantSpecialityEntity> tenantSpecialityEntities = listAll();

        List<TenantSpeciality> tenantSpecialities = new ArrayList<>();
        for (TenantSpecialityEntity tenantSpecialityEntity : tenantSpecialityEntities) {
            tenantSpecialities.add(TenantSpecialityMapper.toDomain(tenantSpecialityEntity));
        }
        return tenantSpecialities;
    }

    @Override
    public TenantSpeciality getTenantSpecialityById(Long id) {
        TenantSpecialityEntity tenantSpecialityEntity = findById(id);
        if (tenantSpecialityEntity == null) {
            return null;
        }
        return TenantSpecialityMapper.toDomain(tenantSpecialityEntity);
    }
}
