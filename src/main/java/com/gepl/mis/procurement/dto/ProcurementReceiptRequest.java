package com.gepl.mis.procurement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcurementReceiptRequest {

    @NotNull(message = "Inventory Id is required")
    private Long inventoryId;
    @Min(value = 1,message = "Received Quantity must be >=1")
    private Integer receivedQty;

    @NotBlank(message = "remarks must be present")
    private String remarks;
}
