package com.gepl.mis.inventory;

import lombok.Data;

@Data
public class InventoryRequest {
    private String itemCode;
    private String itemName;
    private Integer quantity;
    private String unit;
    private String location;
}
