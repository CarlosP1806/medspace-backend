package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.TenantSpecialty;
import com.medspace.infrastructure.entity.TenantSpecialtyEntity;

public class TenantSpecialtyMapper {

    public static TenantSpecialty toDomain(TenantSpecialtyEntity tenantSpecialtyEntity) {
        if (tenantSpecialtyEntity == null) {
            return null;
        }

        TenantSpecialty tenantSpecialty = new TenantSpecialty();
        tenantSpecialty.setId(tenantSpecialtyEntity.getId());
        tenantSpecialty.setName(tenantSpecialtyEntity.getName());

        return tenantSpecialty;
    }

    public static TenantSpecialtyEntity toEntity(TenantSpecialty tenantSpecialty) {
        if (tenantSpecialty == null) {
            return null;
        }

        TenantSpecialtyEntity tenantSpecialtyEntity = new TenantSpecialtyEntity();
        tenantSpecialtyEntity.setId(tenantSpecialty.getId());
        tenantSpecialtyEntity.setName(tenantSpecialty.getName());

        return tenantSpecialtyEntity;
    }

}
