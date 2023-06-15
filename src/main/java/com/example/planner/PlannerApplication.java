package com.example.planner;

import com.example.planner.model.Role;
import com.example.planner.service.RoleService;
import com.example.planner.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }
//    @Bean
//    CommandLineRunner runner(UserService userService, RoleService roleService){
//        return args -> {
//            roleService.saveRole(new Role("USER"));
//            roleService.saveRole(new Role("ADMIN"));
//        };
//    }
}
