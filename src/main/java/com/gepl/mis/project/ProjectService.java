package com.gepl.mis.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    public Project create (ProjectRequest request,String username){
        Project p=new Project();
        p.setProjectCode(request.getProjectCode());
        p.setProjectName(request.getProjectName());
        p.setClientName(request.getClientName());
        p.setPlannedStartDate(request.getPlannedStartDate());
        p.setPlannedEndDate(request.getPlannedEndDate());
        p.setPlannedBudget(request.getPlannedBudget());
        p.setStatus("PLANNED");
        p.setCreatedBy(username);

        return repository.save(p);
    }
}
