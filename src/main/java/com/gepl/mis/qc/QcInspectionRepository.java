package com.gepl.mis.qc;

import com.gepl.mis.production.ProductionOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QcInspectionRepository
        extends JpaRepository<QcInspection, Long> {

    List<QcInspection> findByProductionOrderId(Long productionOrderId);
    List<QcInspection> findByProjectId(Long projectId);
    Page<QcInspection> findAll(Pageable pageable);

}
