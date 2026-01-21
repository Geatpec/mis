package com.gepl.mis.cash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CashLedgerRepository extends JpaRepository<CashLedger, Long> {

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM CashLedger c
        WHERE c.projectId = :projectId
          AND c.type = 'OUT'
    """)
    BigDecimal totalCashOut(@Param("projectId") Long projectId);

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM CashLedger c
        WHERE c.projectId = :projectId
          AND c.type = 'IN'
    """)
    BigDecimal totalCashIn(@Param("projectId") Long projectId);

    // Total cash OUT by category for a project
    @Query("""
    SELECT COALESCE(SUM(c.amount), 0)
    FROM CashLedger c
    WHERE c.projectId = :projectId
      AND c.type = 'OUT'
      AND c.category = :category
""")
    BigDecimal totalCashOutByCategory(
            @Param("projectId") Long projectId,
            @Param("category") String category
    );

    /* ================= ORG LEVEL ================= */

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM CashLedger c
        WHERE c.type = 'IN'
    """)
    BigDecimal totalCashInOrg();

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM CashLedger c
        WHERE c.type = 'OUT'
    """)
    BigDecimal totalCashOutOrg();
}
