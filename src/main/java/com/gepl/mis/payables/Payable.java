package com.gepl.mis.payables;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payables")
@Data
public class Payable extends BaseEntity {
    @Column(name="vendor_name",nullable = false)
    private String vendorName;

    @Column(name="project_id",nullable = false)
    private  Long projectId;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name = "invoice_date", nullable=false)
    private LocalDate invoiceDate;

    @Column(name = "due_date", nullable=false)
    private LocalDate dueDate;

    @Column(name = "invoice_amount",nullable = false)
    private BigDecimal invoiceAmount;

    @Column(name = "paid_amount")
    private BigDecimal paidAmount=BigDecimal.ZERO;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_by")
    private String createdBy;
}
