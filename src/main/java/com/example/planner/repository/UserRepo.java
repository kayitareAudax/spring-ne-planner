package com.example.planner.repository;

import com.example.planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User,UUID> {
    public User findUserByEmail(String email);
}
