package com.spring_security.config;

import com.spring_security.service.AuthUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final AuthUserService authUserService;

    public CustomUserDetailsService(AuthUserService authUserService){
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserService.loadUserByUsername(username);
    }
}
