package com.gepl.mis.automation.engine;

import com.gepl.mis.automation.alert.Alert;
import com.gepl.mis.automation.alert.AlertRepository;
import com.gepl.mis.automation.alert.AlertService;
import com.gepl.mis.automation.rule.AutomationRule;
import com.gepl.mis.automation.rule.AutomationRuleRepository;
import com.gepl.mis.automation.rule.AutomationRuleService;
import com.gepl.mis.kpi.KpiService;
import com.gepl.mis.kpi.dto.ProjectKpiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.compare;

@Service
public class AutomationEngineService {

    @Autowired
    private AutomationRuleRepository ruleRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AutomationRuleService ruleService;

    @Autowired
    private KpiService kpiService;

    public int evaluateProjectRules(Long projectId) {

        List<AutomationRule> rules = ruleService.getActiveRules();
        ProjectKpiResponse kpi = kpiService.projectKpis(projectId);

        int alertCount = 0;

        for (AutomationRule rule : rules) {

            if (!"PROJECT".equals(rule.getScope())) continue;
            if (!"BUDGET_UTILIZATION".equals(rule.getMetric())) continue;

            boolean triggered = compare(
                    BigDecimal.valueOf(kpi.getBudgetUtilizationPercent().doubleValue()),
                    rule.getOperator(),
                    rule.getThreshold()
            );


            if (triggered) {
                alertService.createAlert(
                        "PROJECT",
                        projectId,
                        "Project exceeded budget threshold: " + rule.getThreshold() + "%",
                        rule.getSeverity()
                );
                alertCount++;
            }
        }

        return alertCount;
    }
    private boolean compare(BigDecimal actual, String operator, BigDecimal threshold) {
        return switch (operator) {
            case ">" -> actual.compareTo(threshold) > 0;
            case "<" -> actual.compareTo(threshold) < 0;
            case ">=" -> actual.compareTo(threshold) >= 0;
            case "<=" -> actual.compareTo(threshold) <= 0;
            default -> false;
        };
    }



}
