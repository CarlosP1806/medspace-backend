package com.medspace.infrastructure.dto.notification;

import com.medspace.domain.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetNotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private String createdAt;

    public GetNotificationDTO(Notification notification) {
        if (notification != null) {
            this.id = notification.getId();
            this.userId = notification.getUser() != null ? notification.getUser().getId() : null;
            this.message = notification.getMessage();
            this.createdAt =
                    notification.getCreatedAt() != null ? notification.getCreatedAt().toString()
                            : null;
        }
    }
}
