package com.gepl.mis.dashboard.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExecutiveDashboardResponse {
    private Long totalProjects;
    private Long activeProjects;

    private BigDecimal totalCashIn;
    private BigDecimal totalCashOut;
    private BigDecimal netCashPosition;

    private Long openAlerts;

    private String overallHealth;
}
