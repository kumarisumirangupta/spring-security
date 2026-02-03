package com.spring_security.controller;

import com.spring_security.dto.UserDto;
import com.spring_security.entity.AuthUser;
import com.spring_security.repository.AuthUserRepository;
import com.spring_security.service.AuthUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    final AuthUserService authUserService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test Api");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> registerUser(@RequestBody UserDto user){
        return ResponseEntity.ok(authUserService.saveUser(user));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthUser> login(@RequestBody UserDto user){

        return ResponseEntity.ok(authUserService.saveUser(user));
    }

}
