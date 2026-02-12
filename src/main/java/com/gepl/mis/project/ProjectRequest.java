package com.gepl.mis.project;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProjectRequest {
    @NotBlank
    private String projectCode;
    @NotBlank
    private String projectName;
    @NotBlank
    private String clientName;

    @NotBlank
    private String status;

    @NotNull
    private LocalDate plannedStartDate;
    @NotNull
    private LocalDate plannedEndDate;

    @DecimalMin(value = "0.01", message = "PlannedBudget must be greater than 0")
    private BigDecimal plannedBudget;
}
