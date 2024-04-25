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
		Users users = userRepository.findUsersById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users userAuth = userRepository.findByEmail(auth.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("not found  "+ auth.getName()));
        tasks.setTask_name(tasksDto.getTask_name());
		tasks.setCompleted(tasksDto.getCompleted());
        tasks.setLevel(tasksDto.getLevel());
		tasks.setStatus(tasksDto.getStatus());
		tasks.setFile("test");
		tasks.setTargetDate(tasksDto.getTargetDate());
		tasks.setId(id);
		if(users.getId() == userAuth.getId()) {
			taskRepository.save(tasks); 
		}
	} 
} 
