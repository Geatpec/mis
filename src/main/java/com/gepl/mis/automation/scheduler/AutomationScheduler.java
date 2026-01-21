package com.gepl.mis.automation.scheduler;

import com.gepl.mis.automation.engine.AutomationEngineService;
import com.gepl.mis.project.Project;
import com.gepl.mis.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AutomationScheduler {

    @Autowired
    private AutomationEngineService automationEngineService;
    @Autowired
    private ProjectRepository projectRepository;

//    Runs every day at 1 AM
//    Cron: second minute hour day month weekday

    //    @Scheduled(cron = "*/30 * * * * *") for dev only
    @Scheduled(cron= "0 0 1 * * *")
    public void evaluateProjectAutomationRules(){
        log.info("Automation Scheduler started");

        List<Project> projects=projectRepository.findAll();

        int totalAlerts=0;

        for(Project project: projects){
            int alerts=
                    automationEngineService.evaluateProjectRules(project.getId());
            totalAlerts+=alerts;
        }
        log.info("Automation Scheduler completed> Alerts generated:{}",totalAlerts);

    }
}
