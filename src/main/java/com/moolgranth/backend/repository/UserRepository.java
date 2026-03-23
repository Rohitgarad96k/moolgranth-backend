package com.moolgranth.backend.repository;

import com.moolgranth.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Boot automatically translates this method name into a MySQL query!
    Optional<User> findByPhone(String phone);
}