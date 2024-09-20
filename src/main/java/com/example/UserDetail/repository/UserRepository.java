package com.example.UserDetail.repository;

import com.example.UserDetail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}
