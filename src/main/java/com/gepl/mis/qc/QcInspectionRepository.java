package com.gepl.mis.qc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QcInspectionRepository
        extends JpaRepository<QcInspection, Long> {

    List<QcInspection> findByProductionOrderId(Long productionOrderId);
    List<QcInspection> findByProjectId(Long projectId);
}
