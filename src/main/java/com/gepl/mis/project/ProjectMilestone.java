package com.gepl.mis.project;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="project_milestones")
@Data
public class ProjectMilestone extends BaseEntity {
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name="milestone_name",nullable = false)
    private String milestoneName;

    @Column(name="planned_date",nullable = false)
    private LocalDate plannedDate;

    @Column(name="actual_date")
    private LocalDate actualDate;

    private String status;

}
