package com.gepl.mis.production;

import com.gepl.mis.production.dto.ProductionMaterialConsumeRequest;
import com.gepl.mis.production.dto.ProductionOrderRequest;
import com.gepl.mis.production.dto.ProductionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production")
public class ProductionOrderController {
    @Autowired
    private ProductionOrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductionOrderRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.create(request,user));
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<?> start(@PathVariable Long id){
        return ResponseEntity.ok(service.start(id));
    }

    @PostMapping("/{id}/produce")
    public ResponseEntity<?> produce(@PathVariable Long id, @RequestBody ProductionUpdateRequest request){
        return ResponseEntity.ok(service.updateProduction(id,request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> byProject(@PathVariable Long projectId){
        return ResponseEntity.ok(service.getByProjectId(projectId));
    }

    @PostMapping("/{id}/consume")
    public ResponseEntity<?>  consume(@PathVariable Long id, @RequestBody ProductionMaterialConsumeRequest request){
        String user=SecurityContextHolder.getContext().getAuthentication().getName();
        service.consumeMaterial(id,request,user);
        return ResponseEntity.ok("Material consumed");
    }
}

