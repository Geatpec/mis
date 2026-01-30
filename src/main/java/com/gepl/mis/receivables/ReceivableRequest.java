package com.gepl.mis.receivables;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceivableRequest {
    @NotBlank(message = "Client Name is required")
    private String clientName;

    @NotNull(message = "Project is required")
    private Long projectId;

    @NotNull(message = "Invoice is required")
    private String invoiceNo;

    @NotNull
    private LocalDate invoiceDate;

    @NotNull
    private LocalDate dueDate;

    @DecimalMin(value = "0.01", message = "Invoice Amount must be greater than 0")
    private BigDecimal invoiceAmount;
}

