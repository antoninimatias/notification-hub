package com.projetos.notification_hub.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateNotificationRequest(
        @NotBlank @Size(max = 255) String recipient,
        @NotNull Channel channel,
        @NotBlank @Size(max = 1000) String message
) {
}
