package com.gepl.mis.finishedgoods;

import com.gepl.mis.procurement.Procurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinishedGoodsInventoryRepository
        extends JpaRepository<FinishedGoodsInventory, Long> {

    List<FinishedGoodsInventory> findByProjectId(Long projectId);
    Page<FinishedGoodsInventory> findAll(Pageable pageable);
    Optional<FinishedGoodsInventory>
    findByProjectIdAndProductCodeAndProductionOrderId(
            Long projectId,
            String productCode,
            Long productionOrderId
    );


    List<FinishedGoodsInventory> findByProductionOrderId(Long productionOrderId);
}
