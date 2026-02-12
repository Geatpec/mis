package com.gepl.mis.auth.dto;


import com.gepl.mis.auth.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserTableResponse {


    private Long id;


    private String username;


    private Role role;



    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
