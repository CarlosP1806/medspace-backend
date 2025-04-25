package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Notification;
import com.medspace.domain.repository.NotificationRepository;
import com.medspace.infrastructure.entity.NotificationEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.NotificationMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NotificationRepositoryImpl implements NotificationRepository, PanacheRepositoryBase<NotificationEntity, Long> {
    @Inject
    UserRepositoryImpl userRepository;

    @Transactional
    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity = NotificationMapper.toEntity(notification);
        persist(notificationEntity);
        notification = NotificationMapper.toDomain(notificationEntity);
        return notification;
    }

    @Override
    public Notification getNotificationById(Long id) {
        NotificationEntity notificationEntity = findById(id);
        if (notificationEntity == null) {
            throw new NotFoundException("Notification with id " + id + " not found");
        }
        return NotificationMapper.toDomain(notificationEntity);
    }

    @Override
    @Transactional
    public Notification assignNotificationToUser(Long notificationId, Long userId) {
        NotificationEntity notificationEntity = findById(notificationId);
        if (notificationEntity == null) {
            throw new NotFoundException("Notification with id " + notificationId + " not found");
        }

        UserEntity userEntity = userRepository.findById(userId);
        if (userEntity == null) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        notificationEntity.setUser(userEntity);
        persist(notificationEntity);
        return NotificationMapper.toDomain(notificationEntity);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId);
        if (userEntity == null) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        List<NotificationEntity> notificationEntities = find("user", userEntity).list();
        List<Notification> notifications = new ArrayList<>();
        for (NotificationEntity notificationEntity : notificationEntities) {
            notifications.add(NotificationMapper.toDomain(notificationEntity));
        }
        return notifications;
    }

    @Override
    @Transactional
    public void deleteNotificationById(Long id) {
        NotificationEntity notificationEntity = findById(id);
        if (notificationEntity != null) {
            delete(notificationEntity);
        } else {
            throw new NotFoundException("Notification with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public void markAsRead(Long id) {
        NotificationEntity notificationEntity = findById(id);
        if (notificationEntity != null) {
            notificationEntity.setIsRead(true);
            persist(notificationEntity);
        } else {
            throw new NotFoundException("Notification with id " + id + " not found");
        }
    }
} 