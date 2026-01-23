package com.gepl.mis.cash;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CashRequest {
    @NotBlank(message = "Type is required")
    private String type;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "TransactionDate is required")
    private LocalDate txnDate;

    @NotNull(message = "ProjectId is required")
    private Long projectId;

    @NotNull(message = "ReferenceType is required")
    private String referenceType;// MANUAL, RECEIVABLE, PAYABLE

    @NotNull(message = "ReferenceId is required")
    private Long referenceId;

    @NotNull(message = "Category is required")
    private String category;        // EXPENSE, CUSTOMER_PAYMENT, SALARY

}
