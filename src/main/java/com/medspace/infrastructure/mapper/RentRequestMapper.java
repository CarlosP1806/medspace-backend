package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.RentRequest;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.User;

public class RentRequestMapper {

    public static RentRequestEntity toEntity(RentRequest model) {
        if (model == null)
            return null;

        RentRequestEntity e = new RentRequestEntity();
        e.setId(model.getId());

        if (model.getTenant() != null) {
            UserEntity tenantEntity = UserMapper.toEntity(model.getTenant());
            e.setTenant(tenantEntity);
        }

        if (model.getClinic() != null) {
            ClinicEntity clinicEntity = ClinicMapper.toEntity(model.getClinic());
            e.setClinic(clinicEntity);
        }

        e.setCreatedAt(model.getCreatedAt());
        e.setStartDate(model.getStartDate());
        e.setEndDate(model.getEndDate());
        e.setComments(model.getComments());
        e.setStatus(model.getStatus());

        return e;
    }

    public static RentRequest toDomain(RentRequestEntity entity) {
        if (entity == null)
            return null;

        RentRequest m = new RentRequest();
        m.setId(entity.getId());

        if (entity.getTenant() != null) {
            User tenant = UserMapper.toDomain(entity.getTenant());
            m.setTenant(tenant);
        }

        if (entity.getClinic() != null) {
            Clinic clinic = ClinicMapper.toDomain(entity.getClinic());
            m.setClinic(clinic);
        }

        m.setCreatedAt(entity.getCreatedAt());
        m.setStartDate(entity.getStartDate());
        m.setEndDate(entity.getEndDate());
        m.setComments(entity.getComments());
        m.setStatus(entity.getStatus());

        return m;
    }
}
