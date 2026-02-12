package com.gepl.mis.audit;

import com.gepl.mis.auth.dto.AuditTableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query(
            value = """
            SELECT new com.gepl.mis.auth.dto.AuditTableResponse(
                a.id,
                a.username,
                a.module,
                a.action,
                a.note,
                a.createdAt
            )
            FROM AuditLog a
            ORDER BY a.createdAt DESC
        """,
            countQuery = """
            SELECT COUNT(a)
            FROM AuditLog a
        """
    )
   Page<AuditTableResponse> fetchAuditTable(Pageable pageable);

}
