package com.gepl.mis.receivables;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceivablePaymentRequest {
    private BigDecimal amount;
    private LocalDate paymentDate;
}
