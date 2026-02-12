package com.gepl.mis.project;

import com.gepl.mis.procurement.Procurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Project update (Long id, ProjectRequest request,String username){


        Project p=repository.findById(id).orElseThrow(()->new RuntimeException("Project not found with the id:"+ id))
                ;
        p.setProjectCode(request.getProjectCode());
        p.setProjectName(request.getProjectName());
        p.setClientName(request.getClientName());
        p.setPlannedStartDate(request.getPlannedStartDate());
        p.setPlannedEndDate(request.getPlannedEndDate());
        p.setPlannedBudget(request.getPlannedBudget());
        p.setStatus(request.getStatus());
        p.setCreatedBy(username);

        return repository.save(p);
    }

    public Page<Project> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
