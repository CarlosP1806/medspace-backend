package com.medspace.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.repository.TenantSpecialtyRepository;
import com.medspace.infrastructure.entity.TenantSpecialtyEntity;
import com.medspace.infrastructure.mapper.TenantSpecialtyMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenantSpecialtyRepositoryImpl
        implements TenantSpecialtyRepository, PanacheRepositoryBase<TenantSpecialtyEntity, Long> {

    @Override
    public List<TenantSpecialty> getAllTenantSpecialties() {
        List<TenantSpecialtyEntity> tenantSpecialtyEntities = listAll();

        List<TenantSpecialty> tenantSpecialties = new ArrayList<>();
        for (TenantSpecialtyEntity tenantSpecialtyEntity : tenantSpecialtyEntities) {
            tenantSpecialties.add(TenantSpecialtyMapper.toDomain(tenantSpecialtyEntity));
        }
        return tenantSpecialties;
    }

    @Override
    public TenantSpecialty getTenantSpecialtyById(Long id) {
        TenantSpecialtyEntity tenantSpecialtyEntity = findById(id);
        if (tenantSpecialtyEntity == null) {
            return null;
        }
        return TenantSpecialtyMapper.toDomain(tenantSpecialtyEntity);
    }
}
