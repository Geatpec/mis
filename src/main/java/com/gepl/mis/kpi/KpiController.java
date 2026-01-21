package com.gepl.mis.kpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {
    @Autowired
    private KpiService service;

    // Project KPIs
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<?> projectKpis(@PathVariable Long projectId){
        return ResponseEntity.ok(service.projectKpis(projectId));
    }

    // Org KPIs
    @GetMapping("/org")
    public ResponseEntity<?> orgKpis(){
        return ResponseEntity.ok(service.orgKpis());
    }
}
