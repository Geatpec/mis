package com.gepl.mis.finishedgoods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finished-goods")
public class FinishedGoodsInventoryController {
    @Autowired
    private FinishedGoodsInventoryService service;

    @PostMapping("/from-qc/{qcInspectionId}")
    public ResponseEntity moveFromQc(@PathVariable Long qcInspectionId){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(service.moveToFGI(qcInspectionId,user));
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
