package com.gepl.mis.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cpm")
@RequiredArgsConstructor
public class ProjectSummaryController {

    private final ProjectSummaryService service;

    @GetMapping("/projects/{projectId}/summary")
    public ResponseEntity<?> summary(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.summary(projectId));
    }
}

