package com.spring_security.service;

import com.spring_security.dto.UserDto;
import com.spring_security.entity.AuthUser;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {
    AuthUser saveUser(UserDto user);
    AuthUser loadUserByUsername(String username);
}
