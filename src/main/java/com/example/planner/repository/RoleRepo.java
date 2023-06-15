package com.example.planner.repository;

import com.example.planner.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoleRepo extends JpaRepository<Role,UUID> {
    Role findRoleByRoleName(String roleName);
}
