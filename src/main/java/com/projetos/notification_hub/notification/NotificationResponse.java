package com.projetos.notification_hub.notification;

import java.time.Instant;

public record NotificationResponse(
        Long id, String recipient, Channel channel,
        String message, NotificationStatus status,
        Instant createdAt, Instant sentAt
) {
    static NotificationResponse from(Notification n) {
        return new NotificationResponse(n.getId(), n.getRecipient(), n.getChannel(),
                n.getMessage(), n.getStatus(), n.getCreatedAt(), n.getSentAt());
    }
}
