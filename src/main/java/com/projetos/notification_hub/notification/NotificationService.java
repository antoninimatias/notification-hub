package com.projetos.notification_hub.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public NotificationResponse create(CreateNotificationRequest request) {
        Notification notification = new Notification(request.recipient(), request.channel(), request.message());
        Notification n = repository.save(notification);
        return NotificationResponse.from(n);
    }

    public NotificationResponse findById(Long id) {
        return repository.findById(id)
                .map(NotificationResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification " + id + " not found"));
    }

    public Page<NotificationResponse> list(NotificationStatus status, Pageable pageable) {
        return status == null ? repository.findAll(pageable).map(NotificationResponse::from) :
                repository.findByStatus(status, pageable).map(NotificationResponse::from);
    }
}
