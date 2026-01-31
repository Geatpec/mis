package com.gepl.mis.procurement;

import com.gepl.mis.procurement.dto.ProcurementReceiptRequest;
import com.gepl.mis.procurement.dto.ProcurementRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procurement")
public class ProcurementController {
    @Autowired
    private ProcurementService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProcurementRequest p){
        return ResponseEntity.ok(service.createProcurement(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                   @Valid @RequestBody ProcurementRequest p){
        return ResponseEntity.ok(service.updateProcurement(id,p));
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<?> receive(
            @PathVariable Long id,
            @Valid @RequestBody ProcurementReceiptRequest request
    ) {
        String user = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.receiveMaterial(id, request, user)
        );
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> byProject(
            @PathVariable Long projectId,
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                service.byProject(projectId, pageable)
        );
    }

    @GetMapping("/supplier/{supplier}")
    public ResponseEntity<?> bySupplier(
            @PathVariable String supplier,
            @PageableDefault(size = 10)
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                service.bySupplier(supplier, pageable)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
}
