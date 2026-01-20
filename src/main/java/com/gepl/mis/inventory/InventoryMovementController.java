package com.gepl.mis.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory/movement")
public class InventoryMovementController {
    @Autowired
    private InventoryMovementService service;

    @PostMapping
    public ResponseEntity<?> move(@RequestBody InventoryMovementRequest request){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(service.move(request,auth.getName()));
    }

}
