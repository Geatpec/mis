package com.gepl.mis.cash;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cash")
public class CashController {
    @Autowired
    private CashService cashService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CashRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        return ResponseEntity.ok(cashService.create(request,username));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody CashRequest request){
        return ResponseEntity.ok(cashService.update(id,request));
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
        return ResponseEntity.ok(cashService.getAll(pageable));
    }

}
