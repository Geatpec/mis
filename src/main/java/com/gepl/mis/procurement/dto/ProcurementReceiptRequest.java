package com.gepl.mis.procurement.dto;

import lombok.Data;

@Data
public class ProcurementReceiptRequest {
    private Long inventoryId;
    private Integer receivedQty;
    private String remarks;
}
