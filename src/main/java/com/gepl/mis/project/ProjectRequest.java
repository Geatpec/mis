package com.gepl.mis.project;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProjectRequest {
    private String projectCode;
    private String projectName;
    private String clientName;

    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;

    private BigDecimal plannedBudget;
}
