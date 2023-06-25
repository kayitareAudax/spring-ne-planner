package com.example.planner.service;

import com.example.planner.config.JwtService;
import com.example.planner.dto.AuthResponse;
import com.example.planner.dto.LoginRequest;
import com.example.planner.dto.RegisterRequest;
import com.example.planner.model.Role;
import com.example.planner.model.User;
import com.example.planner.repository.RoleRepo;
import com.example.planner.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse registerUser(RegisterRequest request) {
        log.info("trying to register user");
        var user = User.builder().email(request.getEmail()).names(request.getNames()).telephone(request.getTelephone()).password(passwordEncoder.encode(request.getPassword())).build();
        User initial = userRepo.findUserByEmail(request.getEmail());
        log.info("initial User {}", initial);
        if (initial != null) {
            return AuthResponse.builder().user(null).message("User already registered").token(null).build();
        }
        Role role = roleRepo.findRoleByRoleName("USER");
        user.setRole(role);
        userRepo.save(user);
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().user(user).message("User registered successfully").token(token).build();
    }

    public AuthResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepo.findUserByEmail(request.getEmail());
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().message("logged in successfully").token(token).user(user).build();
    }
}
