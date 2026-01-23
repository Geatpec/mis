package com.gepl.mis.production.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductionOrderRequest {

    @NotNull(message = "ProjectId is required")
    private Long projectId;

    @NotNull(message = "Product Code is required")
    private String productCode;
    @NotBlank(message = "Product Name is required")
    private String productName;

    @Min(value = 1, message = "Planned quantity must be >= 1")
    private Integer plannedQuantity;

    @NotBlank(message = "Remarks must be present")
    private String remarks;
}
