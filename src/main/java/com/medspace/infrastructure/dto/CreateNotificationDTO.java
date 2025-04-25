package com.medspace.infrastructure.dto;

import com.medspace.domain.model.Notification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationDTO {
    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    public Notification toNotification() {
        Notification notification = new Notification();
        notification.setMessage(message);
        return notification;
    }
} 