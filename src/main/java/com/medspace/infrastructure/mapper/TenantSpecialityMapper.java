package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.TenantSpeciality;
import com.medspace.infrastructure.entity.TenantSpecialityEntity;

public class TenantSpecialityMapper {

    public static TenantSpeciality toDomain(TenantSpecialityEntity tenantSpecialityEntity) {
        if (tenantSpecialityEntity == null) {
            return null;
        }

        TenantSpeciality tenantSpeciality = new TenantSpeciality();
        tenantSpeciality.setId(tenantSpecialityEntity.getId());
        tenantSpeciality.setName(tenantSpecialityEntity.getName());

        return tenantSpeciality;
    }

    public static TenantSpecialityEntity toEntity(TenantSpeciality tenantSpeciality) {
        if (tenantSpeciality == null) {
            return null;
        }

        TenantSpecialityEntity tenantSpecialityEntity = new TenantSpecialityEntity();
        tenantSpecialityEntity.setId(tenantSpeciality.getId());
        tenantSpecialityEntity.setName(tenantSpeciality.getName());

        return tenantSpecialityEntity;
    }

}
