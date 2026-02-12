package com.gepl.mis.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuditTableResponse {

    private Long id;
    private String username;
    private String module;
    private String action;
    private String note;
    private LocalDateTime createdAt;
}
