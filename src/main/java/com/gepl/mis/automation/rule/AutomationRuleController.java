package com.gepl.mis.automation.rule;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation/rules")
public class AutomationRuleController {
    @Autowired
    private AutomationRuleService service;

    @PostMapping
    public AutomationRule create(@Valid @RequestBody AutomationRule rule){
        return service.createRule(rule);
    }
    @GetMapping
    public List<AutomationRule> list(){
        return service.getAllRules();
    }
    @PostMapping("/{id}/disable")
    public void disable(@PathVariable Long id){
        service.disableRule(id);
    }
}
