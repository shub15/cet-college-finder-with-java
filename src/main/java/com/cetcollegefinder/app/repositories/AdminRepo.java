package com.cetcollegefinder.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetcollegefinder.app.dto.AdminDTO;

public interface AdminRepo extends JpaRepository<AdminDTO, Long> {
    AdminDTO findByName(String name);

    AdminDTO findByEmail(String email);
}
