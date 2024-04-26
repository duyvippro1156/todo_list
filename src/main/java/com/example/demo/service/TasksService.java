package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import  com.example.demo.dto.TasksDto;
import  com.example.demo.model.Tasks;

@Service
public interface TasksService {
	public Tasks createNewTask(TasksDto task);
	
	public List<Tasks> getAllTask();
	
	public Tasks findTaskById(Long id);
	
	public List<Tasks> findAllCompletedTask();
	
	public List<Tasks> findAllInCompleteTask();

	public void deleteTask(Long id);
	
	public Tasks updateTask(TasksDto tasksDto, Long id);

	public boolean checkAuthor (Long id);

	public List<Tasks> searchByTaskName(String keyWord);
}
