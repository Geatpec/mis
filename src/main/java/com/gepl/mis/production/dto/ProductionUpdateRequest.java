package com.gepl.mis.production.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductionUpdateRequest {
    @Min(value = 1, message = "Produced quantity must be >= 1")
    private Integer producedQuantity;
}
