package com.gepl.mis.payables;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayableRequest {
    private String vendorName;
    private Long projectId;

    private String invoiceNo;
    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private BigDecimal invoiceAmount;
}
