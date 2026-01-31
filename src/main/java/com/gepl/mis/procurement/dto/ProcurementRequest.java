package com.gepl.mis.procurement.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;



@Data
public class ProcurementRequest {


    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotBlank(message = "BOM Version is required")
    private String bomVersion;

    @NotBlank(message = "Part Number is required")
    private String partNo;


    private String description;


    private String supplier;

    @Min(value = 1, message = "Planned Quantity must be at least 1")
    private Integer plannedQty;

    @Min(value = 1, message = "Ordered Quantity must be at least 1")
    private Integer orderedQty;

    @Min(value = 0, message = "Received Quantity cannot be negative")
    private Integer receivedQty;

    @DecimalMin(value = "0.1", message = "Rate must be greater than 0")
    private BigDecimal rate;

    @Min(value = 0, message = "Lead Time cannot be negative")
    private Integer leadTime;
}
