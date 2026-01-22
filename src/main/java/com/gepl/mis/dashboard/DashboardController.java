package com.gepl.mis.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService service;

    @GetMapping("/executive")
    public ResponseEntity<?> executive(){
        return ResponseEntity.ok(service.executiveDashboard());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> project(@PathVariable Long projectId){
        return ResponseEntity.ok(service.projectDashboard(projectId));
    }

    @GetMapping("/procurement/{projectId}")
    public ResponseEntity<?> procurement(@PathVariable Long projectId){
        return ResponseEntity.ok(service.procurementDashboard(projectId));
    }

    @GetMapping("/alerts")
    public ResponseEntity<?> alerts(){
        return ResponseEntity.ok(service.alertDashboard());
    }
}
