package com.gepl.mis.qc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qc")
public class QcInspectionController {
    @Autowired
    private QcInspectionService service;

    @PostMapping("/inspect/{productionOrderId}")
    public ResponseEntity<?> inspect(@PathVariable Long productionOrderId,
                                     @Valid @RequestBody QcInspectionRequest request){
        String inspector= SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(service.inspect(productionOrderId, request,inspector));

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
