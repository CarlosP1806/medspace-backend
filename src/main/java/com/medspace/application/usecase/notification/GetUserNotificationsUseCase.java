package com.medspace.application.usecase.notification;

import com.medspace.application.service.NotificationService;
import com.medspace.domain.model.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetUserNotificationsUseCase {
    @Inject
    NotificationService notificationService;

    public List<Notification> execute(Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }
} 