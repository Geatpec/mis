package com.gepl.mis.production.dto;

import lombok.Data;

@Data
public class ProductionMaterialConsumeRequest {
    private Long inventoryId;
    private Integer quantity;
    private String remarks;
}
