package com.medspace.application.usecase.notification;

import com.medspace.application.service.NotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteNotificationUseCase {
    @Inject
    NotificationService notificationService;

    public void execute(Long id) {
        notificationService.deleteNotification(id);
    }
} 