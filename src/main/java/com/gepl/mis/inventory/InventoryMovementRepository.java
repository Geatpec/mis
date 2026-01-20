package com.gepl.mis.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryMovementRepository
        extends JpaRepository<InventoryMovement, Long> {

    // Total inventory consumed (OUT) for a project
    @Query("""
        SELECT COALESCE(SUM(m.quantity), 0)
        FROM InventoryMovement m
        WHERE m.projectId = :projectId
          AND m.movementType = 'OUT'
    """)
    Integer totalInventoryConsumed(@Param("projectId") Long projectId);

    // Total inventory received (IN) for a project (rare, but useful)
    @Query("""
        SELECT COALESCE(SUM(m.quantity), 0)
        FROM InventoryMovement m
        WHERE m.projectId = :projectId
          AND m.movementType = 'IN'
    """)
    Integer totalInventoryAdded(@Param("projectId") Long projectId);
}
