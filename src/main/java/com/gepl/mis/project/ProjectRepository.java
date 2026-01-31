package com.gepl.mis.project;

import com.gepl.mis.procurement.Procurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    long countByStatus(String status);
    Page<Project> findAll (Pageable pageable);
}
