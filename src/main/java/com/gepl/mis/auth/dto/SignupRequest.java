package com.gepl.mis.auth.dto;

import com.gepl.mis.auth.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private Role role;
}
