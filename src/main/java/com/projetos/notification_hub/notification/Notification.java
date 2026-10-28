package com.projetos.notification_hub.notification;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private Instant createdAt;
    private Instant sentAt;

    protected Notification() {
    }

    public Notification(String recipient, Channel channel, String message) {
        this.recipient = recipient;
        this.channel = channel;
        this.message = message;
    }

    @PrePersist
    void prePersist() {
        this.createdAt = Instant.now();
        if (this.status == null) {
            this.status = NotificationStatus.PENDING;
        }
    }

    public Long getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }
}
