package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Notification;
import com.medspace.infrastructure.entity.NotificationEntity;

public class NotificationMapper {
    public static Notification toDomain(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setId(entity.getId());
        notification.setMessage(entity.getMessage());
        notification.setCreatedAt(entity.getCreatedAt());
        notification.setIsRead(entity.getIsRead());
        notification.setUser(UserMapper.toDomain(entity.getUser()));

        return notification;
    }

    public static NotificationEntity toEntity(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationEntity entity = new NotificationEntity();
        entity.setId(notification.getId());
        entity.setMessage(notification.getMessage());
        entity.setCreatedAt(notification.getCreatedAt());
        entity.setIsRead(notification.getIsRead());
        entity.setUser(UserMapper.toEntity(notification.getUser()));

        return entity;
    }
} 