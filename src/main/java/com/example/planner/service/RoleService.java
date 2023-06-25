package com.example.planner.service;

import com.example.planner.model.Role;
import com.example.planner.model.User;
import com.example.planner.repository.RoleRepo;
import com.example.planner.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public User createUserWithRole(User user, Role role) {
        Role newRole = roleRepo.save(role);
        user.setRole(newRole);
        return userRepo.save(user);
    }

    public String saveRoleToUser(String email, String roleName) {
        User user = userRepo.findUserByEmail(email);
        log.info(email);
        log.info(String.valueOf(user));
        if (user == null) {
            log.info("null user");
            return "user does not exixt";
        }
        Role role = roleRepo.findRoleByRoleName(roleName);
        user.setRole(role);
        userRepo.save(user);
        return "role saved to user successfully";
    }

    public Role saveRole(Role role) {
        Role initialRole = roleRepo.findRoleByRoleName(role.getRoleName());
        if (initialRole != null) {
            return initialRole;
        }
        Role role1 = roleRepo.save(role);
        return role1;
    }
}
