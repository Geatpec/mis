package com.gepl.mis.kpi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectKpiResponse {
    private Long projectId;
    private String projectCode;

    private BigDecimal plannedBudget;
    private BigDecimal actualSpend;

    private  BigDecimal budgetUtilizationPercent;
    private BigDecimal cashIn;
    private BigDecimal cashOut;

    private BigDecimal receivableOutstanding;
    private BigDecimal payableOutstanding;

    private Integer inventoryConsumed;

    private String costStatus;
    private String cashFlowStatus;
    private String receivableRisk;
}
