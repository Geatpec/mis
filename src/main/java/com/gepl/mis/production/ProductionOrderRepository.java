package com.gepl.mis.production;

import com.gepl.mis.payables.Payable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder,Long> {
    List<ProductionOrder> findByProjectId(Long projectId);
    List<ProductionOrder> findByStatus(String status);
    Page<ProductionOrder> findAll(Pageable pageable);
}
