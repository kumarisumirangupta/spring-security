package com.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    UserDetailsService userDetails(PasswordEncoder passwordEncoder){
        UserDetails user1 = User.withUsername("Sumiran")
//                .password("{noop}abcd")
                .password(passwordEncoder.encode("abcd"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }*/

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/register")
                        .permitAll()
                        .anyRequest().authenticated()
                ).csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
