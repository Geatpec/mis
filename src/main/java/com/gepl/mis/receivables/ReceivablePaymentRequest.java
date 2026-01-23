package com.gepl.mis.receivables;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceivablePaymentRequest {
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;


    @NotBlank(message = "Payment Date is required")
    private LocalDate paymentDate;
}
