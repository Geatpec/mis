package com.gepl.mis.kpi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrgKpiResponse {
    private Long totalProjects;
    private Long activeProjects;

    private BigDecimal totalCashIn;
    private BigDecimal totalCashOut;
    private BigDecimal netCashPosition;

    private BigDecimal totalReceivableOutstanding;
    private BigDecimal totalPayableOutstanding;

    private String cashHealth;
    private String receivableRisk;
}
