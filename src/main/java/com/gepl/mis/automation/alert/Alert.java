package com.gepl.mis.automation.alert;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "alerts")
public class Alert extends BaseEntity {
    private String scope;
    private Long entityId;

    private String message;
    private String severity;
    private String status;
}
