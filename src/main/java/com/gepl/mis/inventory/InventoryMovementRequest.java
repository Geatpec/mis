package com.gepl.mis.inventory;

import lombok.Data;

@Data
public class InventoryMovementRequest {
    private Long inventoryId;
    private String movementType;
    private Integer quantity;
    private Long projectId;

    private String referenceType;

    private Long referenceId;
    private String remarks;
}
