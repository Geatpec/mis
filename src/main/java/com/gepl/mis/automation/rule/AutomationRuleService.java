package com.gepl.mis.automation.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomationRuleService {
    @Autowired
    private AutomationRuleRepository repository;

    public AutomationRule createRule(AutomationRule rule){
        rule.setStatus("ACTIVE");
        return repository.save(rule);
    }
    public List<AutomationRule> getAllRules(){
        return repository.findAll();
    }

    public List<AutomationRule> getActiveRules(){
        return repository.findByStatus("ACTIVE");
    }
    public void disableRule(Long ruleId){
        AutomationRule rule=repository.findById(ruleId)
                .orElseThrow(()->new RuntimeException("Rule not found"));

        rule.setStatus("DISABLED");
        repository.save(rule);
    }
}
