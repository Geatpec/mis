package com.gepl.mis.finishedgoods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
