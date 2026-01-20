package com.gepl.mis.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectRequest request){
        String user= SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok(service.create(request,user));
    }
}
