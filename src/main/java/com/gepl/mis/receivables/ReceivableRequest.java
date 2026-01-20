package com.gepl.mis.receivables;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceivableRequest {
    private String clientName;
    private Long projectId;

    private String invoiceNo;
    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private BigDecimal invoiceAmount;
}
