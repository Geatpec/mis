package com.gepl.mis.receivables;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receivables")
@Data
public class Receivable extends BaseEntity {
    @Column(name="client_name", nullable = false)
    private String clientName;

    @Column(name="project_id", nullable = false)
    private Long projectId;

    @Column(name="invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name="invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name="due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name="invoice_amount", nullable = false)
    private BigDecimal invoiceAmount;

    @Column(name="received_amount")
    private BigDecimal receivedAmount =BigDecimal.ZERO;

    @Column(name = "tds_applicable", nullable = false)
    private Boolean tdsApplicable = false;

    @Column(name = "tds_rate")
    private BigDecimal tdsRate;

    @Column(name = "tds_amount", nullable = false)
    private BigDecimal tdsAmount = BigDecimal.ZERO;

    @Column(name = "net_amount", nullable = false)
    private BigDecimal netAmount = BigDecimal.ZERO;

    @Column(name = "tds_description", length = 255)
    private String tdsDescription;


    @Column( nullable = false)
    private String status;

    @Column(name="")
    private String createdBy;


}
