package com.gepl.mis.auth;

import com.gepl.mis.auth.dto.AuthResponse;
import com.gepl.mis.auth.dto.LoginRequest;
import com.gepl.mis.auth.dto.ResetPasswordRequest;
import com.gepl.mis.auth.dto.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        authService.signup(request);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/users/{userId}/reset-password")
    @PreAuthorize("hasRole('FOUNDER')")
    public ResponseEntity<?> resetPassword(
            @PathVariable Long userId,
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        String adminUsername =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        authService.resetUserPassword(
                userId,
                adminUsername,
                request.getNewPassword()
        );

        return ResponseEntity.ok("Password reset successfully");
    }
}
