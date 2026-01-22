package com.gepl.mis.dashboard;

import com.gepl.mis.automation.alert.AlertService;
import com.gepl.mis.dashboard.dto.AlertDashboardResponse;
import com.gepl.mis.dashboard.dto.ExecutiveDashboardResponse;
import com.gepl.mis.dashboard.dto.ProcurementDashboardResponse;
import com.gepl.mis.dashboard.dto.ProjectDashboardResponse;
import com.gepl.mis.finishedgoods.FinishedGoodsInventory;
import com.gepl.mis.finishedgoods.FinishedGoodsInventoryRepository;
import com.gepl.mis.kpi.KpiService;
import com.gepl.mis.kpi.dto.OrgKpiResponse;
import com.gepl.mis.procurement.Procurement;
import com.gepl.mis.procurement.ProcurementService;
import com.gepl.mis.qc.QcInspection;
import com.gepl.mis.qc.QcInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private KpiService kpiService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private ProcurementService procurementService;
    @Autowired
    private QcInspectionRepository qcRepository;
    @Autowired
    private FinishedGoodsInventoryRepository fgiRepository;


    public ExecutiveDashboardResponse executiveDashboard(){
        OrgKpiResponse org=kpiService.orgKpis();

        ExecutiveDashboardResponse dto= new ExecutiveDashboardResponse();
        dto.setTotalProjects(org.getTotalProjects());
        dto.setActiveProjects(org.getActiveProjects());
        dto.setTotalCashIn(org.getTotalCashIn());
        dto.setTotalCashOut(org.getTotalCashOut());
        dto.setNetCashPosition(org.getNetCashPosition());
        dto.setOpenAlerts(alertService.openAlertCount());

        dto.setOverallHealth(org.getNetCashPosition().compareTo(BigDecimal.ZERO)>=0?"GOOD":"WARNING");
        return dto;
    }


    public ProjectDashboardResponse projectDashboard(Long projectId){
        var kpi=kpiService.projectKpis(projectId);

        ProjectDashboardResponse dto=new ProjectDashboardResponse();
        dto.setProjectId(projectId);
        dto.setPlannedBudget(kpi.getPlannedBudget());
        dto.setActualSpend(kpi.getActualSpend());
        dto.setBudgetUtilizationPercent(kpi.getBudgetUtilizationPercent());

        dto.setProducedQty(kpi.getInventoryConsumed());

        dto.setAcceptedQty(qcRepository.findByProjectId(projectId).stream().mapToInt(QcInspection::getAcceptedQty).sum());
        dto.setFinishedGoodsQty(fgiRepository.findByProjectId(projectId).stream().mapToInt(FinishedGoodsInventory::getQuantity).sum());
        return dto;

    }

    public ProcurementDashboardResponse procurementDashboard(Long projectId) {
        List<Procurement> list = procurementService.byProject(projectId);

        ProcurementDashboardResponse dto = new ProcurementDashboardResponse();
        dto.setProjectId(projectId);
        dto.setPlannedQty(list.stream().mapToInt(Procurement::getPlannedQty).sum());
        dto.setOrderedQty(list.stream().mapToInt(Procurement::getOrderedQty).sum());
        dto.setReceivedQty(list.stream().mapToInt(Procurement::getReceivedQty).sum());
        dto.setExcessQty(list.stream().mapToInt(Procurement::getExcessQty).sum());

        dto.setTotalProcurementValue(
                list.stream()
                        .map(Procurement::getTotalValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        return dto;
    }

    public AlertDashboardResponse alertDashboard() {

        AlertDashboardResponse dto = new AlertDashboardResponse();
        dto.setOpenAlerts(alertService.openAlertCount());
        dto.setHighSeverity(alertService.countBySeverity("HIGH"));
        dto.setMediumSeverity(alertService.countBySeverity("MEDIUM"));
        dto.setLowSeverity(alertService.countBySeverity("LOW"));

        return dto;
    }
    }




















