package com.gepl.mis.payables;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayablePaymentRequest {
    private BigDecimal amount;
    private LocalDate paymentDate;
}
