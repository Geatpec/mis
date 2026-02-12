package com.gepl.mis.inventory;

import com.gepl.mis.procurement.Procurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByItemCode(String itemCode);
    Page<Inventory> findAll(Pageable pageable);

}
