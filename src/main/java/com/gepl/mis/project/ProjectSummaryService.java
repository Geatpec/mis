package com.gepl.mis.project;

import com.gepl.mis.cash.CashLedger;
import com.gepl.mis.cash.CashLedgerRepository;
import com.gepl.mis.inventory.InventoryMovementRepository;
import com.gepl.mis.payables.PayableRepository;
import com.gepl.mis.project.dto.ProjectSummaryResponse;
import com.gepl.mis.receivables.ReceivableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProjectSummaryService {

    private final ProjectRepository projectRepository;
    private final CashLedgerRepository cashRepository;
    private final ReceivableRepository receivableRepository;
    private final PayableRepository payableRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    public ProjectSummaryResponse summary(Long projectId) {

        Project p = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        BigDecimal cashOut = cashRepository.totalCashOut(projectId);
        BigDecimal cashIn = cashRepository.totalCashIn(projectId);

        BigDecimal receivableOutstanding =
                receivableRepository.totalReceivableOutstanding(projectId);

        BigDecimal payableOutstanding =
                payableRepository.totalPayableOutstanding(projectId);

        Integer inventoryConsumed =
                inventoryMovementRepository.totalInventoryConsumed(projectId);

        BigDecimal actualSpend = cashOut;
        BigDecimal budgetVariance =
                p.getPlannedBudget().subtract(actualSpend);

        ProjectSummaryResponse res = new ProjectSummaryResponse();
        res.setProjectId(p.getId());
        res.setProjectCode(p.getProjectCode());
        res.setProjectName(p.getProjectName());

        res.setPlannedBudget(p.getPlannedBudget());
        res.setActualSpend(actualSpend);
        res.setBudgetVariance(budgetVariance);

        res.setCashIn(cashIn);
        res.setCashOut(cashOut);

        res.setReceivableOutstanding(receivableOutstanding);
        res.setPayableOutstanding(payableOutstanding);

        res.setInventoryConsumed(inventoryConsumed);

        res.setFinancialStatus(
                budgetVariance.signum() >= 0 ? "ON_TRACK" : "OVER_BUDGET"
        );

        return res;
    }
}
