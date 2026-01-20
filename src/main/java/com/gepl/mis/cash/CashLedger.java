package com.gepl.mis.cash;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_ledger")
@Data
public class CashLedger extends BaseEntity {
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;

    @Column(name= "txn_date", nullable = false)
    private LocalDate txnDate;
    @Column(name = "created_by")
    private String CreatedBy;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name =" referenceId")
    private Long referenceId;

    @Column(name = "category")
    private String category;

}
