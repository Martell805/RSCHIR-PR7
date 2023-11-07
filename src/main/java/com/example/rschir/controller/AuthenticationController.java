package com.example.rschir.controller;

import com.example.rschir.service.AuthenticationService;
import com.example.rschir.entity.RedisEntity;
import com.example.rschir.model.RedisEntityDao;
import com.example.rschir.model.JwtAuthenticationResponse;
import com.example.rschir.model.SignInRequest;
import com.example.rschir.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RedisEntityDao redisEntityDao;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> singUp(@RequestBody SignUpRequest request, Principal principal){
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        redisEntityDao.save(RedisEntity.builder().userEmail(request.getEmail()).token(response.getToken()).build());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> singIn(@RequestBody SignInRequest request){
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        redisEntityDao.save(RedisEntity.builder().userEmail(request.getEmail()).token(response.getToken()).build());
        return ResponseEntity.ok(response);
    }
}
