package com.example.rschir.controller;

import com.example.rschir.entity.Role;
import com.example.rschir.entity.User;
import com.example.rschir.repository.RoleRepo;
import com.example.rschir.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @RequestMapping(value = "/api/admin/setRole", method = RequestMethod.PUT)
    public ResponseEntity<String> setRole(@RequestParam String role, @RequestParam Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        switch (role) {
            case "ROLE_ADMIN" -> {
                Set<Role> roles = user.getRoles();
                roles.add(roleRepo.save(Role.builder().name("ROLE_ADMIN").build()));
                user.setRoles(roles);
            }
            case "ROLE_SELLER" -> {
                Set<Role> roles = user.getRoles();
                roles.add(roleRepo.save(Role.builder().name("ROLE_SELLER").build()));
                user.setRoles(roles);
            }
            case "ROLE_USER" -> {
                Set<Role> roles = user.getRoles();
                roles.add(roleRepo.save(Role.builder().name("ROLE_USER").build()));
                user.setRoles(roles);
            }
        }

        userRepo.save(user);
        return ResponseEntity.ok("Roles successfully updated");
    }

}
