package com.gepl.mis.production;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder,Long> {
    List<ProductionOrder> findByProjectId(Long projectId);
    List<ProductionOrder> findByStatus(String status);
}
