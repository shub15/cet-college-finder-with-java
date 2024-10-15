package com.cetcollegefinder.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetcollegefinder.app.dto.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
