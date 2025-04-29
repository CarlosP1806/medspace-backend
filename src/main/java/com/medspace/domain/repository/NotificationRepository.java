package com.medspace.domain.repository;

import com.medspace.domain.model.Notification;
import java.util.List;

public interface NotificationRepository {
    public Notification save(Notification notification);
    public Notification getNotificationById(Long id);
    public Notification assignNotificationToUser(Long notificationId, Long userId);
    public List<Notification> getNotificationsByUserId(Long userId);
    public void deleteNotificationById(Long id);
    public void markAsRead(Long id);
} 