package com.gepl.mis.procurement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcurementRepository extends JpaRepository<Procurement,Long> {
    List<Procurement> findByProjectId(Long projectId);
    Page<Procurement> findByProjectId(Long projectId, Pageable pageable);
    List<Procurement> findBySupplier(String supplier);
    Page<Procurement> findBySupplier(String supplier, Pageable pageable);
    Page<Procurement> findAll( Pageable pageable);
}
