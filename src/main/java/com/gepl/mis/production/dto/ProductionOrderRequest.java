package com.gepl.mis.production.dto;

import lombok.Data;

@Data
public class ProductionOrderRequest {
    private Long projectId;

    private String productCode;
    private String productName;

    private Integer plannedQuantity;
    private String remarks;
}
