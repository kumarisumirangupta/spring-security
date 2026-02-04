package com.spring_security.controller;

import com.spring_security.dto.UserDto;
import com.spring_security.entity.AuthUser;
import com.spring_security.repository.AuthUserRepository;
import com.spring_security.service.AuthUserService;
import com.spring_security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    final AuthUserService authUserService;
    final AuthenticationManager authenticationManager;
    final JwtUtil jwtUtil;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test Api");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> registerUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(authUserService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        return ResponseEntity.ok(
                Map.of(
                        "accessToken", jwtUtil.generateAccessToken(user),
                        "refreshToken", jwtUtil.generateRefreshToken(user),
                        "userDetails", user
                )
        );
    }

}
