package com.medspace.application.usecase.notification;

import com.medspace.application.service.NotificationService;
import com.medspace.domain.model.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MarkNotificationAsReadUseCase {
    @Inject
    NotificationService notificationService;

    public void execute(Long id, Long userId) {
        Notification notification = notificationService.getNotificationById(id);
        if (notification == null) {
            throw new NotFoundException("Notification not found");
        }

        if (!notification.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to mark this notification as read");
        }

        notificationService.markAsRead(id);
    }
} 