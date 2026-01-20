package com.gepl.mis.cash;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CashRequest {
    private String type;
    private BigDecimal amount;
    private String description;
    private LocalDate txnDate;
    private Long projectId;
    private String referenceType;   // MANUAL, RECEIVABLE, PAYABLE
    private Long referenceId;
    private String category;        // EXPENSE, CUSTOMER_PAYMENT, SALARY

}
