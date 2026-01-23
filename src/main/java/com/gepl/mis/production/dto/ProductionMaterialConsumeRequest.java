package com.gepl.mis.production.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductionMaterialConsumeRequest {
    @NotNull(message = "Inventory Id is required")
    private Long inventoryId;

    @Min(value = 1, message = "Quantity must be >= 1")
    private Integer quantity;

    @NotBlank(message = "Remarks must be present")
    private String remarks;
}
