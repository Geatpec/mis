package com.gepl.mis.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectSummaryResponse {
    private Long projectId;
    private String projectCode;
    private String projectName;

    private BigDecimal plannedBudget;
    private BigDecimal actualSpend;
    private BigDecimal budgetVariance;

    private BigDecimal cashIn;
    private BigDecimal cashOut;

    private BigDecimal receivableOutstanding;
    private BigDecimal payableOutstanding;

    private Integer inventoryConsumed;

    private String financialStatus;

}
