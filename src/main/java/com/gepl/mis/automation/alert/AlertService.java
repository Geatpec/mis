package com.gepl.mis.automation.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository repository;
    /* ---------- Create ---------- */
    public Alert createAlert(String scope, Long entityId, String message, String severity) {
        Alert alert = new Alert();
        alert.setScope(scope);
        alert.setEntityId(entityId);
        alert.setMessage(message);
        alert.setSeverity(severity);
        alert.setStatus("OPEN");

        return repository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return repository.findAll();
    }
    /* ---------- lifecycle ---------- */
    public void acknowledge(Long alertId) {
        Alert alert = repository.findById(alertId).orElseThrow(()->new RuntimeException("Alert not found"));

        alert.setStatus("ACKNOWLEDGED");
        repository.save(alert);
    }
    public void close(Long alertId){
        Alert alert= repository.findById(alertId)
                .orElseThrow(()->new RuntimeException("Alert not found"));

        alert.setStatus("CLOSED");
        repository.save(alert);
    }

    /* ---------- Queries ---------- */

    public List<Alert> listAll() {
        return repository.findAll();
    }

    public List<Alert> listFiltered(
            String severity,
            String status,
            String scope
    ) {
        return repository.filterAlerts(severity, status, scope);
    }

    public long openAlertCount() {
        return repository.countOpenAlerts();
    }

    /* ---------- Helper ---------- */

    private Alert get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
    }

    public long countBySeverity(String severity) {
        return repository.countOpenBySeverity(severity);
    }
}
