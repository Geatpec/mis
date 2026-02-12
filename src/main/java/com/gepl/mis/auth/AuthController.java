package com.gepl.mis.auth;

import com.gepl.mis.auth.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        authService.signup(request);
        return ResponseEntity.ok(Map.of("message","User created"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/{userId}/reset-password")
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

        return ResponseEntity.ok(Map.of("message","Password reset successfully"));
    }

    @PreAuthorize("hasRole('FOUNDER')")
    @GetMapping("/users")
    public Page<UserTableResponse> usersTable(
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        return authService.getUsersTable(pageable);
    }

    @PreAuthorize("hasAnyRole('FOUNDER')")
    @GetMapping("/audit")
    public ResponseEntity<?> auditTable(
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return ResponseEntity.ok(
                authService.getAuditTable(pageable)
        );
    }


}
