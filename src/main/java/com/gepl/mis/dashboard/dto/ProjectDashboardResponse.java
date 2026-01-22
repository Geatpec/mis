package com.gepl.mis.dashboard.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectDashboardResponse {
    private Long projectId;

    private BigDecimal plannedBudget;
    private BigDecimal actualSpend;
    private BigDecimal budgetUtilizationPercent;

    private Integer producedQty;
    private Integer acceptedQty;
    private Integer finishedGoodsQty;
}
