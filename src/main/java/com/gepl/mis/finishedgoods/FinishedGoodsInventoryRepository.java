package com.gepl.mis.finishedgoods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinishedGoodsInventoryRepository
        extends JpaRepository<FinishedGoodsInventory, Long> {

    List<FinishedGoodsInventory> findByProjectId(Long projectId);


    List<FinishedGoodsInventory> findByProductionOrderId(Long productionOrderId);
}
