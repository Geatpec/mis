package com.gepl.mis.audit;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="audit_logs")
@Data
public class AuditLog extends BaseEntity {
    private String username;
    private String role;
    private String module;
    private String action;

    private String note;

}
