package com.example.rschir.service;

import com.example.rschir.entity.Role;
import com.example.rschir.entity.User;
import com.example.rschir.repository.RoleRepo;
import com.example.rschir.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveNewUser(User user){
        User userFromBd = userRepo.findUserByEmail(user.getEmail()).orElse(null);
        if (userFromBd == null){
            Role studentRole = roleRepo.findById(1L).orElse(null);
            user.setRoles(Collections.singleton(studentRole));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        }else{
            return false;
        }
    }
    public User save(User user, String roleName){
        Role role = Role.builder().name(roleName).build();
        role = roleRepo.save(role);
        user.setRoles(Collections.singleton(role));
        user.setPassword(user.getPassword());
        return userRepo.save(user);
    }

    public UserDetails findUserByEmail(String email){
        return userRepo.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }
    public User getUserByEmail(String email){
        return userRepo.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User replaceUser(User newUser, Long id){
        User user = userRepo.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(newUser.getEmail()));
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.save(user);
    }

    public User getUserFromBd(Long id){
        return userRepo.findById(id).orElse(null);
    }
}
