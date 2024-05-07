package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping("/") 
    public ResponseEntity<List<Projects>> getAllTasks() { 
        return ResponseEntity.ok(projectsService.getAllProject()); 
    } 

    @GetMapping("/{id}") 
    public ResponseEntity<Projects> getTask(@PathVariable Long id) { 
        Projects project = projectsService.findProjectById(id);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(project, HttpStatus.OK);
        }   
    } 

    @PutMapping("/{id}") 
    public ResponseEntity<Projects> updateTask(@PathVariable Long id, @RequestBody ProjectDto project) { 
        if (projectsService.checkProjectAuthor(id)){
            return new ResponseEntity<Projects>(projectsService.updateProject(project, id), HttpStatus.OK);
        }else {
            return new ResponseEntity<Projects>(projectsService.findProjectById(id), HttpStatus.BAD_REQUEST); 
        }
        
    } 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) { 
        if (projectsService.checkProjectAuthor(id)){
            projectsService.deActiveProject(id);
            return ResponseEntity.ok(true); 
        }else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); 
        }
    } 


}
