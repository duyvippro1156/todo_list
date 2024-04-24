package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TasksDto;
import com.example.demo.model.Tasks; 
import com.example.demo.service.TasksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 

import java.util.List; 


@RestController
@RequestMapping("/api/tasks") 
@CrossOrigin(origins = "*")
public class TasksController {
   
    @Autowired
    private TasksService taskService; 

    @GetMapping("/") 
    public ResponseEntity<List<Tasks>> getAllTasks() { 
        return ResponseEntity.ok(taskService.getAllTask()); 
    } 
    @GetMapping("/completed") 
    public ResponseEntity<List<Tasks>> getAllCompletedTasks() { 
        return ResponseEntity.ok(taskService.findAllCompletedTask()); 
    } 
    @GetMapping("/incomplete") 
    public ResponseEntity<List<Tasks>> getAllIncompleteTasks() { 
        return ResponseEntity.ok(taskService.findAllInCompleteTask()); 
    } 
    @PostMapping("/") 
    public ResponseEntity<Tasks> createTask(@RequestBody TasksDto task) { 
        return ResponseEntity.ok(taskService.createNewTask(task)); 
    } 
    @PutMapping("/{id}") 
    public ResponseEntity<TasksDto> updateTask(@PathVariable Long id, @RequestBody TasksDto task) { 
        taskService.updateTask(task, id);
        return new ResponseEntity<>(HttpStatus.OK);
    } 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) { 
        taskService.deleteTask(id); 
        return ResponseEntity.ok(true); 
    } 
}
    
