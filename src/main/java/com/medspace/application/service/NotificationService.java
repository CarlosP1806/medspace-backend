package com.medspace.application.service;

import com.medspace.domain.model.Notification;
import com.medspace.domain.repository.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class NotificationService {
    @Inject
    NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(Instant.now());
        notification = notificationRepository.save(notification);
        return notification;
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.getNotificationById(id);
    }

    public Notification assignNotificationToUser(Long notificationId, Long userId) {
        return notificationRepository.assignNotificationToUser(notificationId, userId);
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.getNotificationsByUserId(userId);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteNotificationById(id);
    }

    public void markAsRead(Long id) {
        notificationRepository.markAsRead(id);
    }
}