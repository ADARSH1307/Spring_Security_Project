package com.ust.Ust_Ptojects.controller;

import com.ust.Ust_Ptojects.model.Project;
import com.ust.Ust_Ptojects.model.ProjectStatus;
import com.ust.Ust_Ptojects.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {
//    @Autowired
//    private ProjectRepository projectRepo;

//    @PostMapping("/create")
//    public String createProject(@RequestBody Project project) {
//        project.setProjectStatus(ProjectStatus.INPROGRESS);
//        projectRepo.save(project);
//        return "Project " + project.getProjectName() + " created successfully";
//    }
//
//    @PutMapping("/updateStatus/{projectId}")
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String updateProjectStatus(@PathVariable int projectId, @RequestParam ProjectStatus status) {
//        Project project = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
//        project.setProjectStatus(status);
//        projectRepo.save(project);
//        return "Project " + project.getProjectName() + " status updated to " + status;
//    }
//
//    @GetMapping("/getAllProjects")
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public List<Project> getAllProjects() {
//        return projectRepo.findAll();
//    }

    @Autowired
    private ProjectRepository Repository;

    @PostMapping("/create")
    public String createPost(@RequestBody Project project, Principal principal) {
        project.setProjectStatus(ProjectStatus.INPROGRESS);
        project.setUsername(principal.getName());
        project.setPsnumber(project.getPsnumber());
        Repository.save(project);
        return principal.getName() + " Your post published successfully , Required ADMIN/MODERATOR Action !";
    }
    @GetMapping("/approveAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String approveAll() {
        Repository.findAll().stream().filter(post -> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .forEach(post -> {
                    post.setProjectStatus(ProjectStatus.COMPLETED);
                    Repository.save(post);
                });
        return "Approved all posts !";
    }

    @GetMapping("/removeProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String removeProject(@PathVariable int projectId) {
        Project project = Repository.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.REJECTED);
        Repository.save(project);
        return "project Rejected !!";
    }
    @GetMapping("/updateProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String updateProject(@PathVariable int projectId) {
        Project project = Repository.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.COMPLETED);
        Repository.save(project);
        return "project updated  !!";
    }
    @GetMapping("/viewAll")
    public List<Project> viewAll(){
        return Repository.findAll().stream()
                .filter(post -> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .collect(Collectors.toList());
    }






}
