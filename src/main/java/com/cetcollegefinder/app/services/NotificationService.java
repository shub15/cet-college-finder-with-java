package com.cetcollegefinder.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetcollegefinder.app.dto.Notification;
import com.cetcollegefinder.app.repositories.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
