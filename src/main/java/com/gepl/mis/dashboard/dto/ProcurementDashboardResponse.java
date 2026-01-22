package com.gepl.mis.dashboard.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProcurementDashboardResponse {
    private Long projectId;
    private Integer plannedQty;
    private Integer orderedQty;
    private Integer receivedQty;
    private Integer excessQty;

    private BigDecimal totalProcurementValue;
}
