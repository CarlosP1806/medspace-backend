package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.RentRequest;
import com.medspace.infrastructure.entity.RentRequestEntity;

public class RentRequestMapper {

    public static RentRequestEntity toEntity(RentRequest model) {
        if (model == null)
            return null;
        RentRequestEntity e = new RentRequestEntity();
        e.setId(model.getId());
        e.setCustomerId(model.getCustomerId());
        e.setSpaceId(model.getSpaceId());
        e.setRequestedAt(model.getRequestedAt());
        e.setStatus(model.getStatus());
        return e;
    }

    public static RentRequest toDomain(RentRequestEntity entity) {
        if (entity == null)
            return null;
        RentRequest m = new RentRequest();
        m.setId(entity.getId());
        m.setCustomerId(entity.getCustomerId());
        m.setSpaceId(entity.getSpaceId());
        m.setRequestedAt(entity.getRequestedAt());
        m.setStatus(entity.getStatus());
        return m;
    }
}
