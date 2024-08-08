package com.nasefa.springfinalproject.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByUsername("admin") == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin123");
            userService.saveUser(user);
        }
    }
}
