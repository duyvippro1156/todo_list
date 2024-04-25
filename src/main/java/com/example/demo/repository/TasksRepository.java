package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tasks;

import java.util.List; 

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> { 
	List<Tasks> findByCompletedTrue(); 
	List<Tasks> findByCompletedFalse(); 
	List<Tasks> findAll(); 
	Tasks getById(Long id); 
} 

