package com.spring_security.repository;

import com.spring_security.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    @Query("SELECT U FROM AuthUser U WHERE U.username=:username")
    AuthUser loadUserByUsername(String username);
}
