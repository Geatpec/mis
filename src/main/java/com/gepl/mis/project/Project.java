package com.gepl.mis.project;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="projects")
@Data
public class Project extends BaseEntity {
    @Column(name = "project_code",nullable = false,unique = true)
    private String projectCode;

    @Column(name = "project_name",nullable = false)
    private String projectName;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "planned_start_date")
    private LocalDate plannedStartDate;

    @Column(name = "planned_end_date")
    private LocalDate plannedEndDate;

    @Column(name = "planned_budget")
    private BigDecimal plannedBudget;

    @Column(name ="created_by" )
    private String createdBy;


    private String status;

}

