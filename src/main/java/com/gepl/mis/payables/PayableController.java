package com.gepl.mis.payables;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payables")
public class PayableController {
    @Autowired
    private PayableService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PayableRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.create(request,user));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> pay(@PathVariable Long id,@Valid @RequestBody PayablePaymentRequest request){
        String user=SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(service.pay(id, request,user));
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
