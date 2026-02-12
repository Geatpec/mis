package com.gepl.mis.receivables;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receivables")
public class ReceivableController {
    @Autowired
    private ReceivableService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReceivableRequest request)throws BadRequestException {
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.create(request,user));

    }
    @PostMapping("/{id}/pay")
    public ResponseEntity<?> receivePayment(@PathVariable Long id,@Valid @RequestBody ReceivablePaymentRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.receivePayment(id,request,user));
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

