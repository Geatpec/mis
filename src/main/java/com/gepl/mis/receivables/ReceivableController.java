package com.gepl.mis.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receivables")
public class ReceivableController {
    @Autowired
    private ReceivableService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReceivableRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.create(request,user));

    }
    @PostMapping("/{id}/pay")
    public ResponseEntity<?> receivePayment(@PathVariable Long id, @RequestBody ReceivablePaymentRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.receivePayment(id,request,user));
    }
}

