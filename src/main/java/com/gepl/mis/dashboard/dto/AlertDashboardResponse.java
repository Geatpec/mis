package com.gepl.mis.dashboard.dto;

import lombok.Data;

@Data
public class AlertDashboardResponse {
    private Long openAlerts;
    private Long highSeverity;
    private Long mediumSeverity;

    private Long lowSeverity;
}
