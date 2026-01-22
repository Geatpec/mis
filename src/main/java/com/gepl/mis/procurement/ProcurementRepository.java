package com.gepl.mis.procurement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcurementRepository extends JpaRepository<Procurement,Long> {
    List<Procurement> findByProjectId(Long projectId);

    List<Procurement> findBySupplier(String supplier);
}
