package com.cetcollegefinder.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetcollegefinder.app.dto.AdminDTO;
import com.cetcollegefinder.app.dto.UserDTO;
import com.cetcollegefinder.app.repositories.AdminRepo;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public Boolean authUser(UserDTO user) {
        AdminDTO existingUser = adminRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            String existingUserPassword = existingUser.getPassword();
            String userPassword = user.getPassword();
            return userPassword.equals(existingUserPassword);
        } else {
            return false;
        }
    }
}
