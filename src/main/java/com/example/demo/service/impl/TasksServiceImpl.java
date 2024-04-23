package com.example.demo.service.impl;

import com.example.demo.dto.TasksDto;
import com.example.demo.model.Tasks;
import com.example.demo.repository.TasksRepository;
import com.example.demo.service.TasksService;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import java.util.List; 

@Service
public class TasksServiceImpl implements TasksService { 
	@Autowired
	private TasksRepository taskRepository; 
	
	@Override
	public Tasks createNewTask(TasksDto tasksDto) { 
		Tasks tasks = new Tasks();
        tasks.setTask_name(tasksDto.getTask_name());
        tasks.setCompleted(tasksDto.isCompleted());
		return taskRepository.save(tasks); 
	} 
	
	@Override
	public List<Tasks> getAllTask() { 
		return taskRepository.findAll(); 
	} 
	
	@Override
	public Tasks findTaskById(Long id) { 
		return taskRepository.getById(id); 
	} 
	
	@Override
	public List<Tasks> findAllCompletedTask() { 
		return taskRepository.findByCompletedTrue(); 
	} 
	
	@Override
	public List<Tasks> findAllInCompleteTask() { 
		return taskRepository.findByCompletedFalse(); 
	} 
	
	@Override
	public void deleteTask(Long id) { 
		taskRepository.deleteById(id); 
	} 
	
	@Override
	public void updateTask(TasksDto tasksDto, Long id) { 
		Tasks tasks = taskRepository.getById(id);

        tasks.setTask_name(tasksDto.getTask_name());
        tasks.setCompleted(tasksDto.isCompleted());
		tasks.setId(id);
		taskRepository.save(tasks); 
	} 
} 
