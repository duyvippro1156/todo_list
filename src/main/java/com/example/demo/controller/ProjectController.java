package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Projects;
import com.example.demo.service.ProjectsService;

@RestController
@RequestMapping("/api/project") 
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    ProjectsService projectsService;

    @PostMapping("/") 
    public ResponseEntity<Projects> createTask(@RequestBody ProjectDto project) {
        projectsService.createNewProject(project);
        return new ResponseEntity<Projects>(HttpStatus.CREATED); 
    } 
}
