package com.medspace.application.usecase.notification;

import com.medspace.application.service.NotificationService;
import com.medspace.domain.model.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateNotificationUseCase {
    @Inject
    NotificationService notificationService;

    public Notification execute(Notification notification) {
        return notificationService.createNotification(notification);
    }
} 