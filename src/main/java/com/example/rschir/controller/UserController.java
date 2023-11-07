package com.example.rschir.controller;

import com.example.rschir.entity.User;
import com.example.rschir.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("user-info/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserFromBd(id);
        return ResponseEntity.ok(user);
    }
}
