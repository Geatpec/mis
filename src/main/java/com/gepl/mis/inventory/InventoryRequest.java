package com.gepl.mis.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequest {

    @NotBlank
    private String itemCode;
    @NotBlank
    private String itemName;
    @Min(value = 1,message = "Received Quantity must be >=1")
    private Integer quantity;
    @NotBlank
    private String unit;
    @NotBlank
    private String location;
}
