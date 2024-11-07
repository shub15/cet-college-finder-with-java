package com.cetcollegefinder.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetcollegefinder.app.dto.Notification;
import com.cetcollegefinder.app.repositories.NotificationRepository;

import java.util.List;
import java.util.Optional;

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

    public Notification updateNotification(Long id, Notification updatedNotification) {
        Optional<Notification> existingNotificationOpt = notificationRepository.findById(id);
        if (existingNotificationOpt.isPresent()) {
            Notification existingNotification = existingNotificationOpt.get();

            existingNotification.setText(updatedNotification.getText());

            return notificationRepository.save(existingNotification);
        } else {
            throw new RuntimeException("Notification not found with ID: " + id);
        }
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
