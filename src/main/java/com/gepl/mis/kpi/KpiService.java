package com.gepl.mis.kpi;

import com.gepl.mis.cash.CashLedgerRepository;
import com.gepl.mis.inventory.InventoryMovementRepository;
import com.gepl.mis.kpi.dto.OrgKpiResponse;
import com.gepl.mis.kpi.dto.ProjectKpiResponse;
import com.gepl.mis.payables.PayableRepository;
import com.gepl.mis.project.Project;
import com.gepl.mis.project.ProjectRepository;
import com.gepl.mis.project.ProjectService;
import com.gepl.mis.receivables.ReceivableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class KpiService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CashLedgerRepository cashRepository;

    @Autowired
    private ReceivableRepository receivableRepository;

    @Autowired
    private PayableRepository payableRepository;

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

//   ================PROJECT KPI===================
    public ProjectKpiResponse projectKpis(Long projectId){
        Project p=projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("Project not found"));

        BigDecimal cashOut= cashRepository.totalCashOut(projectId);
        BigDecimal cashIn=cashRepository.totalCashIn(projectId);

        BigDecimal receivableOutstanding=receivableRepository.totalReceivableOutstanding(projectId);

        BigDecimal payableOutstanding=payableRepository.totalPayableOutstanding(projectId);

        Integer inventoryConsumed= inventoryMovementRepository.totalInventoryConsumed(projectId);

        BigDecimal utilization= cashOut.divide(p.getPlannedBudget(),2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        ProjectKpiResponse res= new ProjectKpiResponse();

        res.setProjectId(p.getId());
        res.setProjectCode(p.getProjectCode());
        res.setPlannedBudget(p.getPlannedBudget());
        res.setActualSpend(cashOut);
        res.setBudgetUtilizationPercent(utilization);

        res.setCashIn(cashIn);
        res.setCashOut(cashOut);

        res.setReceivableOutstanding(receivableOutstanding);
        res.setPayableOutstanding(payableOutstanding);
        res.setInventoryConsumed(inventoryConsumed);

        // Cost status

        if(utilization.compareTo(BigDecimal.valueOf(90))<0){
            res.setCostStatus("SAFE");
        }
        else if(utilization.compareTo(BigDecimal.valueOf(100))<=0){
            res.setCostStatus("WARNING");
        }else{
            res.setCostStatus("OVERSPEND");
        }

        // Cashflow
        res.setCashFlowStatus(cashIn.compareTo(cashOut)>=0 ? "POSITIVE": "NEGATIVE");

        // Receivable risk
        if(receivableOutstanding.compareTo(BigDecimal.valueOf(100000))>0){
            res.setReceivableRisk("HIGH");
        }
        else if(receivableOutstanding.compareTo(BigDecimal.valueOf(50000))>0){
            res.setReceivableRisk("MEDIUM");
        }
        else {
            res.setReceivableRisk("LOW");
        }
        return res;
    }

    // ================= ORG KPI ==================

    public OrgKpiResponse orgKpis(){
        BigDecimal cashIn =cashRepository.totalCashInOrg();
        BigDecimal cashOut=cashRepository.totalCashOutOrg();
        BigDecimal netCash=cashIn.subtract(cashOut);

        BigDecimal receivableOutstanding= receivableRepository.totalReceivableOutstandingOrg();

        BigDecimal payableOutstanding= payableRepository.totalPayableOutstandingOrg();

        OrgKpiResponse res= new OrgKpiResponse();
        res.setTotalProjects(projectRepository.count());
        res.setActiveProjects(projectRepository.countByStatus("IN_PROGRESS")+ projectRepository.countByStatus("PLANNED")+ projectRepository.countByStatus("ON_HOLD"));

        res.setTotalCashIn(cashIn);
        res.setTotalCashOut(cashOut);
        res.setNetCashPosition(netCash);

        res.setTotalReceivableOutstanding(receivableOutstanding);
        res.setTotalPayableOutstanding(payableOutstanding);

//        Cash Health

        if(netCash.compareTo(BigDecimal.ZERO)>=0){
            res.setCashHealth("GOOD");
        }else if(netCash.compareTo(BigDecimal.valueOf(-50000))>0){
            res.setCashHealth("WARNING");
        }
        else{
            res.setCashHealth("CRITICAL");
        }


        /* Receivable risk */
        if (receivableOutstanding.compareTo(BigDecimal.valueOf(100000)) > 0) {
            res.setReceivableRisk("HIGH");
        } else if (receivableOutstanding.compareTo(BigDecimal.valueOf(50000)) > 0) {
            res.setReceivableRisk("MEDIUM");
        } else {
            res.setReceivableRisk("LOW");
        }

        return res;



    }


}
