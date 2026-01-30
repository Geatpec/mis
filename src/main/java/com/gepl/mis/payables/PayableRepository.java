package com.gepl.mis.payables;

import com.gepl.mis.cash.CashLedger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PayableRepository extends JpaRepository<Payable, Long> {

    // Total outstanding payables for a project
    @Query("""
        SELECT COALESCE(SUM(p.invoiceAmount - p.paidAmount), 0)
        FROM Payable p
        WHERE p.projectId = :projectId
          AND p.status <> 'PAID'
    """)
    BigDecimal totalPayableOutstanding(@Param("projectId") Long projectId);

    // Total payable amount for a project
    @Query("""
        SELECT COALESCE(SUM(p.invoiceAmount), 0)
        FROM Payable p
        WHERE p.projectId = :projectId
    """)
    BigDecimal totalPayableAmount(@Param("projectId") Long projectId);

    // Total amount paid for a project
    @Query("""
        SELECT COALESCE(SUM(p.paidAmount), 0)
        FROM Payable p
        WHERE p.projectId = :projectId
    """)
    BigDecimal totalPaidAmount(@Param("projectId") Long projectId);

    /* ================= ORG LEVEL ================= */

    @Query("""
        SELECT COALESCE(SUM(p.invoiceAmount - p.paidAmount), 0)
        FROM Payable p
        WHERE p.status <> 'PAID'
    """)
    BigDecimal totalPayableOutstandingOrg();
    Page<Payable> findAll(Pageable pageable);
}

