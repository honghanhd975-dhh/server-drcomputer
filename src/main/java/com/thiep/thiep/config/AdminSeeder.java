package com.thiep.thiep.config;

import com.thiep.thiep.entity.User;
import com.thiep.thiep.model.AuthProvider;
import com.thiep.thiep.model.Role;
import com.thiep.thiep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {
        userRepo.findByEmail(adminEmail).orElseGet(() -> {
            User u = new User();
            u.setEmail(adminEmail);
            u.setName("Admin");
            u.setRole(Role.ADMIN);
            u.setProvider(AuthProvider.LOCAL);
            u.setPasswordHash(encoder.encode(adminPassword));
            return userRepo.save(u);
        });
    }
}
