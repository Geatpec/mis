package com.gepl.mis.automation.rule;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "automation_rules")
@Data
public class AutomationRule extends BaseEntity {
    private String ruleName;
    private String scope;
    private String metric;
    private String operator;
    @Column(precision = 10, scale = 2)
    private BigDecimal threshold;

    private String severity;
    private String status;
}
