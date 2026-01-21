package com.gepl.mis.automation.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation/alerts")
public class AlertController {
    @Autowired
    private AlertService service;

    @GetMapping
    public List<Alert> list(){
        return service.getAllAlerts();
    }

    @PostMapping("/{id}/ack")
    public void acknowledge(@PathVariable Long id){
        service.acknowledge(id);
    }

    @PostMapping("/{id}/close")
    public void close(@PathVariable long id){
        service.close(id);
    }

    @GetMapping("/count/open")
    public ResponseEntity<?> openCount() {
        return ResponseEntity.ok(service.openAlertCount());
    }
}
