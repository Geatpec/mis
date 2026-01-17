package com.gepl.mis.auth;

import com.gepl.mis.auth.dto.AuthResponse;
import com.gepl.mis.auth.dto.LoginRequest;
import com.gepl.mis.auth.dto.SignupRequest;
import com.gepl.mis.config.JwtService;
import com.gepl.mis.config.SecurityBeansConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

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




}
