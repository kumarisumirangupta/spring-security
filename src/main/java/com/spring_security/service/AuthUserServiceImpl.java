package com.spring_security.service;

import com.spring_security.dto.UserDto;
import com.spring_security.entity.AuthUser;
import com.spring_security.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthUserServiceImpl implements AuthUserService {

    final AuthUserRepository authUserRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public AuthUser saveUser(UserDto user) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(user.username());
        authUser.setPassword(passwordEncoder.encode(user.password()));
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser loadUserByUsername(String username) {
        return authUserRepository.loadUserByUsername(username);
    }
}
