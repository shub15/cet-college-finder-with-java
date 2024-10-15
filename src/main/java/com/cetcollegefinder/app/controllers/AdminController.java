package com.cetcollegefinder.app.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.cetcollegefinder.app.dto.UserDTO;
import com.cetcollegefinder.app.services.AdminService;
import com.cetcollegefinder.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @PostMapping("/auth")
    public Boolean auth(@RequestBody UserDTO user) {
        return service.authUser(user);
    }
}
