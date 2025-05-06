package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.RentRequestDay;
import com.medspace.infrastructure.entity.RentRequestDayEntity;

public class RentRequestDayMapper {
    public static RentRequestDay toDomain(RentRequestDayEntity rentRequestDayEntity) {
        if (rentRequestDayEntity == null) {
            return null;
        }

        RentRequestDay rentRequestDay = new RentRequestDay();
        rentRequestDay.setId(rentRequestDayEntity.getId());
        rentRequestDay.setDate(rentRequestDayEntity.getDate());
        rentRequestDay.setCreatedAt(rentRequestDayEntity.getCreatedAt());
        rentRequestDay
                .setRentRequest(RentRequestMapper.toDomain(rentRequestDayEntity.getRentRequest()));

        return rentRequestDay;
    }

    public static RentRequestDayEntity toEntity(RentRequestDay rentRequestDay) {
        if (rentRequestDay == null) {
            return null;
        }

        RentRequestDayEntity rentRequestDayEntity = new RentRequestDayEntity();
        rentRequestDayEntity.setId(rentRequestDay.getId());
        rentRequestDayEntity.setDate(rentRequestDay.getDate());
        rentRequestDayEntity.setCreatedAt(rentRequestDay.getCreatedAt());
        rentRequestDayEntity
                .setRentRequest(RentRequestMapper.toEntity(rentRequestDay.getRentRequest()));

        return rentRequestDayEntity;
    }

}
