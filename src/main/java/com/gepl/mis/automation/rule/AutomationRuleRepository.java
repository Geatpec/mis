package com.gepl.mis.automation.rule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomationRuleRepository
        extends JpaRepository<AutomationRule, Long> {

    List<AutomationRule> findByStatus(String status);
}