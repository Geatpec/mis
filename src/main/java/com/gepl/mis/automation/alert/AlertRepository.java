package com.gepl.mis.automation.alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByStatus(String status);

    List<Alert> findBySeverity(String severity);

    @Query("""
        SELECT a
        FROM Alert a
        WHERE (:severity IS NULL OR a.severity = :severity)
          AND (:status IS NULL OR a.status = :status)
          AND (:scope IS NULL OR a.scope = :scope)
    """)
    List<Alert> filterAlerts(
            @Param("severity") String severity,
            @Param("status") String status,
            @Param("scope") String scope
    );

    @Query("""
        SELECT COUNT(a)
        FROM Alert a
        WHERE a.status = 'OPEN'
    """)
    long countOpenAlerts();

    @Query("""
        SELECT COUNT(a)
        FROM Alert a
        WHERE a.status = 'OPEN'
          AND a.severity = :severity
    """)
    long countOpenBySeverity( @Param("severity") String severity);
}
