package com.gepl.mis.auth;

import com.gepl.mis.audit.AuditLogRepository;
import com.gepl.mis.auth.dto.*;
import com.gepl.mis.config.JwtService;
import com.gepl.mis.config.SecurityBeansConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    private JwtService jwtService;

    public void signup(SignupRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        User user= new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request){
        User user= userRepository.findByUsername(request.getUsername()).orElseThrow(()->new RuntimeException("Invalid Credentials"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword()))
            throw new RuntimeException("Invalid Credentials");

        String token= jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getUsername()
        );

    }

    @Transactional
    public void resetUserPassword(
            Long userId,
            String adminUsername,
            String newPassword
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);


    }

        @Transactional
        public Page<UserTableResponse> getUsersTable(
                Pageable pageable
        ) {
            return userRepository.fetchUsersForDashboard(
                    pageable
            );
        }

    public Page<AuditTableResponse> getAuditTable(Pageable pageable) {
        return auditLogRepository.fetchAuditTable(pageable);
    }
        }








