package com.medspace.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.medspace.domain.model.Notification;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.NotificationRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
class NotificationServiceTest {
    @InjectMock
    NotificationRepository notificationRepository;

    @Inject
    NotificationService notificationService;

    private Notification notification;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFullName("Test User");
        notification = new Notification();
        notification.setId(1L);
        notification.setUser(user);
        notification.setMessage("Test message");
        notification.setCreatedAt(Instant.now());
        notification.setIsRead(false);
    }

    @Test
    void testCreateNotification() {
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);
        Notification created = notificationService.createNotification(notification);
        assertNotNull(created);
        assertEquals("Test message", created.getMessage());
    }

    @Test
    void testGetNotificationById() {
        when(notificationRepository.getNotificationById(1L)).thenReturn(notification);
        Notification found = notificationService.getNotificationById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testAssignNotificationToUser() {
        when(notificationRepository.assignNotificationToUser(1L, 1L)).thenReturn(notification);
        Notification assigned = notificationService.assignNotificationToUser(1L, 1L);
        assertNotNull(assigned);
    }

    @Test
    void testGetNotificationsByUserId() {
        when(notificationRepository.getNotificationsByUserId(1L))
                .thenReturn(Arrays.asList(notification));
        List<Notification> notifications = notificationService.getNotificationsByUserId(1L);
        assertEquals(1, notifications.size());
    }

    @Test
    void testDeleteNotification() {
        doNothing().when(notificationRepository).deleteNotificationById(1L);
        notificationService.deleteNotification(1L);
        verify(notificationRepository, times(1)).deleteNotificationById(1L);
    }

    @Test
    void testMarkAsRead() {
        doNothing().when(notificationRepository).markAsRead(1L);
        notificationService.markAsRead(1L);
        verify(notificationRepository, times(1)).markAsRead(1L);
    }
}
