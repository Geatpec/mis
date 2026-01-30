package com.gepl.mis.receivables;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReceivableRepository extends JpaRepository<Receivable, Long> {

    // Total outstanding receivables for a project
    @Query("""
        SELECT COALESCE(SUM(r.invoiceAmount - r.receivedAmount), 0)
        FROM Receivable r
        WHERE r.projectId = :projectId
          AND r.status <> 'PAID'
    """)
    BigDecimal totalReceivableOutstanding(@Param("projectId") Long projectId);

    // Total invoiced amount for a project
    @Query("""
        SELECT COALESCE(SUM(r.invoiceAmount), 0)
        FROM Receivable r
        WHERE r.projectId = :projectId
    """)
    BigDecimal totalInvoicedAmount(@Param("projectId") Long projectId);

    // Total amount received for a project
    @Query("""
        SELECT COALESCE(SUM(r.receivedAmount), 0)
        FROM Receivable r
        WHERE r.projectId = :projectId
    """)
    BigDecimal totalReceivedAmount(@Param("projectId") Long projectId);


    /* ================= ORG LEVEL ================= */

    @Query("""
        SELECT COALESCE(SUM(r.invoiceAmount - r.receivedAmount), 0)
        FROM Receivable r
        WHERE r.status <> 'PAID'
    """)
    BigDecimal totalReceivableOutstandingOrg();

    Page<Receivable> findAll(@Nonnull Pageable pageable);
}
