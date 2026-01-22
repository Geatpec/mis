package com.gepl.mis.procurement;

import com.gepl.mis.procurement.dto.ProcurementReceiptRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procurement")
public class ProcurementController {
    @Autowired
    private ProcurementService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Procurement p){
        return ResponseEntity.ok(service.create(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Procurement p){
        return ResponseEntity.ok(service.update(id,p));
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<?> receive(
            @PathVariable Long id,
            @RequestBody ProcurementReceiptRequest request
    ) {
        String user = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.receiveMaterial(id, request, user)
        );
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> byProject(@PathVariable Long projectId){
        return ResponseEntity.ok(service.byProject(projectId));
    }
    @GetMapping("/supplier/{supplier}")
    public ResponseEntity<?> bySupplier(@PathVariable String supplier){
        return ResponseEntity.ok(service.bySupplier(supplier));
    }
}
