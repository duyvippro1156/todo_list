package com.example.demo.service.impl;

import com.example.demo.dto.TasksDto;
import com.example.demo.model.Tasks;
import com.example.demo.model.Users;
import com.example.demo.repository.TasksRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TasksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TasksServiceImpl implements TasksService { 

	@Autowired
	private TasksRepository taskRepository; 

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Tasks createNewTask(TasksDto tasksDto) { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("not found  "+ auth.getName()));
		Tasks tasks = new Tasks();
        tasks.setTask_name(tasksDto.getTask_name());
		tasks.setCompleted(tasksDto.getCompleted());
        tasks.setLevel(tasksDto.getLevel());
		tasks.setStatus(tasksDto.getStatus());
		tasks.setFile("test");
		tasks.setTargetDate(tasksDto.getTargetDate());
		tasks.setUsers(Collections.singleton(user));
		return taskRepository.save(tasks); 
	} 
	
	@Override
	public List<Tasks> getAllTask() { 
		return taskRepository.findAllWithoutDeleted(); 
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
		if(checkAuthor(id)) {
			taskRepository.softDelete(id); 
		} 
		 
	} 
	
	@Override
	public Tasks updateTask(TasksDto tasksDto, Long id) { 	
		Tasks tasks = taskRepository.getById(id);
		tasks.setTask_name(tasksDto.getTask_name());
		tasks.setCompleted(tasksDto.getCompleted());
		tasks.setLevel(tasksDto.getLevel());
		tasks.setStatus(tasksDto.getStatus());
		tasks.setFile("test");
		tasks.setTargetDate(tasksDto.getTargetDate());
		tasks.setId(id);
		if (checkAuthor(id)) {
			return taskRepository.save(tasks);
		} else {
			return taskRepository.getById(id);
		}
	}

	@Override
	public boolean checkAuthor(Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users users = userRepository.findUsersByTaskId(id);
		Users userAuth = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("not found  "+ auth.getName()));
		if(users.getId() == userAuth.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Tasks> searchByTaskName(String keyWord) {
		return taskRepository.searchByTaskname(keyWord);
	} 

	
	
} 
