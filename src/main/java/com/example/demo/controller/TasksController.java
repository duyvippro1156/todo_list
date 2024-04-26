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

    @GetMapping("/{id}") 
    public ResponseEntity<Tasks> getTask(@PathVariable Long id) { 
        Tasks tasks = taskService.findTaskById(id);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }   
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
        return new ResponseEntity<Tasks>(taskService.createNewTask(task),HttpStatus.CREATED); 
    } 

    @PutMapping("/{id}") 
    public ResponseEntity<Tasks> updateTask(@PathVariable Long id, @RequestBody TasksDto task) { 
        if (taskService.checkAuthor(id)){
            return new ResponseEntity<Tasks>(taskService.updateTask(task, id), HttpStatus.OK);
        }else {
            return new ResponseEntity<Tasks>(taskService.findTaskById(id), HttpStatus.BAD_REQUEST); 
        }
        
    } 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) { 
        if (taskService.checkAuthor(id)){
            taskService.deleteTask(id);
            return ResponseEntity.ok(true); 
        }else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); 
        }
    } 

    @PostMapping("/search") 
    public ResponseEntity<List<Tasks>> seachByTaskName(@RequestParam String keyWord) {
        List<Tasks> listtTasks = taskService.searchByTaskName(keyWord);
        if (listtTasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listtTasks, HttpStatus.OK);
        }
    } 
}
    
