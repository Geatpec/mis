package com.gepl.mis.bootstrap;

import com.gepl.mis.auth.User;
import com.gepl.mis.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.gepl.mis.auth.Role.FOUNDER;

@Component
@RequiredArgsConstructor
public class FounderBootstrapRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // 🔒 Guard 1: Founder already exists
        if (userRepository.existsByRole(FOUNDER)) {
            return;
        }

        // 🔒 Guard 2: Username already taken
        if (userRepository.existsByUsername("Vansh Raj")) {
            return;
        }

        User founder = new User();
        founder.setUsername("Vansh Raj");
        founder.setPassword(
                passwordEncoder.encode("Founder@123")
        );
        founder.setRole(FOUNDER);
//        founder.setEnabled(true);

        userRepository.save(founder);

        System.out.println("✅ Founder user created successfully");
    }
}
